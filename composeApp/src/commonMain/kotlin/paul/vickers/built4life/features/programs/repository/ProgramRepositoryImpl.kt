package paul.vickers.built4life.features.programs.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.features.days.model.Day
import paul.vickers.built4life.features.programs.model.Program
import paul.vickers.built4life.features.programs.model.ProgramsWithDays

class ProgramRepositoryImpl(
    private val b4lDatabase: B4LDatabase,
) : ProgramRepository {

    private val queries = b4lDatabase.programsQueries

    override val programs: Flow<List<Program>> = queries.getAll().asFlow().mapToList(
        Dispatchers.Default
    ).map { programEntities ->
        programEntities.map {
            Program(
                id = it.id,
                title = it.title
            )
        }
    }
    override val programsWithDays: Flow<List<ProgramsWithDays>> =
        queries.getProgramsWithDays().asFlow().mapToList(
            Dispatchers.Default
        ).map { programEntities ->
            programEntities.groupBy {
                it.id
                }.map { program ->
                ProgramsWithDays(
                    program = Program(
                        id = program.value.first().id,
                        title = program.value.first().title
                    ),
                    days = program.value.map {
                        Day(
                            id = it.id_,
                            title = it.title_,
                            programId = it.program_id
                        )
                    }
                )
            }
        }



    override suspend fun getById(id: Long): Program? {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(program: Program) {
        withContext(Dispatchers.Default) {
            queries.upsert(
                id = program.id,
                title = program.title
            )
        }
    }

    override suspend fun insert(program: Program) {
        withContext(Dispatchers.Default) {
            queries.insert(
                title = program.title
            )
        }
    }

    override suspend fun update(program: Program) {
        withContext(Dispatchers.Default) {
            program.id?.let {
                queries.update(
                    title = program.title,
                    id = it
                )
            }
        }
    }

    override suspend fun delete(program: Program) {
        withContext(Dispatchers.Default) {
            program.id?.let { queries.delete(it) }
        }

    }

}