package paul.vickers.built4life.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.model.Program

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

    override suspend fun delete(program: Program) {
        withContext(Dispatchers.Default) {
            program.id?.let { queries.delete(it) }
        }

    }

}