package paul.vickers.built4life.repository

import kotlinx.coroutines.flow.Flow
import paul.vickers.built4life.model.Program

interface ProgramRepository {
    val programs: Flow<List<Program>>
    suspend fun getById(id: Long): Program?
    suspend fun upsert(program: Program)
    suspend fun delete(program: Program)

}