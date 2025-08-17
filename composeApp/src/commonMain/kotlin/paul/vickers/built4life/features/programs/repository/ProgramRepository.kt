package paul.vickers.built4life.features.programs.repository

import kotlinx.coroutines.flow.Flow
import paul.vickers.built4life.features.programs.model.Program
import paul.vickers.built4life.features.programs.model.ProgramsWithDays

interface ProgramRepository {
    val programs: Flow<List<Program>>
    val programsWithDays: Flow<List<ProgramsWithDays>>
    suspend fun getById(id: Long): Program?
    suspend fun upsert(program: Program)

    suspend fun insert(program: Program)

    suspend fun update(program: Program)

    suspend fun delete(program: Program)


}