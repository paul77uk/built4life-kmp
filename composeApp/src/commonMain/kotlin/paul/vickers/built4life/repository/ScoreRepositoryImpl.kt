package paul.vickers.built4life.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.model.Score

class ScoreRepositoryImpl(
    private val b4lDatabase: B4LDatabase,
) : ScoreRepository {

    private val queries = b4lDatabase.scoreQueries

    override val scores: Flow<List<Score>> =
        queries.getAllScores().asFlow().mapToList(
            Dispatchers.Default
        ).map { scoreEntities ->
            scoreEntities.map { scoreEntity ->
                Score(
                    id = scoreEntity.id,
                    reps = scoreEntity.reps,
                    workoutId = scoreEntity.workout_id,
                    weight = scoreEntity.weight
                )
            }
        }

    override fun getScoresByWorkoutId(workoutId: Long): Flow<List<Score>> {
        return queries.getScoresByWorkoutId(workoutId).asFlow().mapToList(
            Dispatchers.Default
        ).map { scoreEntities ->
            scoreEntities.map { scoreEntity ->
                Score(
                    id = scoreEntity.id,
                    reps = scoreEntity.reps,
                    workoutId = scoreEntity.workout_id,
                    weight = scoreEntity.weight
                )
            }
        }
    }

    override suspend fun getById(id: Long): Score? {
        return withContext(Dispatchers.Default) {
            queries.getScoreById(id).executeAsOneOrNull()?.let { scoreEntity ->
                Score(
                    id = scoreEntity.id,
                    reps = scoreEntity.reps,
                    workoutId = scoreEntity.workout_id,
                    weight = scoreEntity.weight
                )
            }
        }
    }

    override suspend fun upsert(score: Score) {
        withContext(Dispatchers.Default) {
            score.workoutId?.let {
                queries.upsertScore(
                    id = score.id,
                    reps = score.reps,
                    weight = score.weight,
                    workout_id = it
                )
            }
        }
    }

    override suspend fun delete(score: Score) {
        withContext(Dispatchers.Default) {
            score.id?.let { queries.deleteScore(it) }
        }
    }

    override suspend fun getLastScore() {
        return withContext(Dispatchers.Default) {
            queries.getLastScore()
        }
    }
}