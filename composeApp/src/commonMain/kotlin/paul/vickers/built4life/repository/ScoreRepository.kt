package paul.vickers.built4life.repository


import kotlinx.coroutines.flow.Flow
import paul.vickers.built4life.model.Score

interface ScoreRepository {

    val scores: Flow<List<Score>>
    fun getScoresByWorkoutId(workoutId: Long): Flow<List<Score>>
    suspend fun getById(id: Long): Score?
    suspend fun upsert(score: Score)
    suspend fun delete(score: Score)
    suspend fun getLastScore()
}