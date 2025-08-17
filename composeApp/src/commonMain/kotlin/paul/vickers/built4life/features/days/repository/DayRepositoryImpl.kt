package paul.vickers.built4life.features.days.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.features.days.model.Day

class DayRepositoryImpl(
    private val b4lDatabase: B4LDatabase,
) : DayRepository {

    private val queries = b4lDatabase.daysQueries

    override fun getDaysByProgramId(programId: Long): Flow<List<Day>> {
        return queries.getDaysByProgramId(programId).asFlow().mapToList(
            Dispatchers.Default
        ).map { dayEntities ->
            dayEntities.map {
                Day(
                    id = it.id,
                    title = it.title,
                    programId = it.program_id
                )
            }
        }
    }

    override suspend fun upsert(day: Day) {
        withContext(Dispatchers.Default) {
            if (day.id == null)
                queries.insert(
                    title = day.title,
                    program_id = day.programId,
                )
            else
                queries.update(
                    id = day.id,
                    title = day.title,
                    program_id = day.programId
                )

        }
    }

    override suspend fun delete(day: Day) {
        withContext(Dispatchers.Default) {
            day.id?.let { queries.delete(it) }
        }
    }

}