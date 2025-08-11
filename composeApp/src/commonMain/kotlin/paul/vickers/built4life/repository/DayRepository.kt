package paul.vickers.built4life.repository

import kotlinx.coroutines.flow.Flow
import paul.vickers.built4life.model.Day

interface DayRepository {

    fun getDaysByProgramId(programId: Long): Flow<List<Day>>

    suspend fun upsert(day: Day)

    suspend fun delete(day: Day)
}