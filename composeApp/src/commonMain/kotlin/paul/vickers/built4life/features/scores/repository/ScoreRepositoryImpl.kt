package paul.vickers.built4life.features.scores.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.features.scores.model.Score

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
                    weight = scoreEntity.weight,
                    dayWorkoutId = scoreEntity.day_workout_id
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
                    weight = scoreEntity.weight,
                    dayWorkoutId = scoreEntity.day_workout_id
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
                    weight = scoreEntity.weight,
                    dayWorkoutId = scoreEntity.day_workout_id
                )
            }
        }
    }

    override suspend fun upsert(score: Score) {
        withContext(Dispatchers.Default) {
            if (score.id == null) {
                score.workoutId?.let {
                    queries.insertScore(
                        reps = score.reps,
                        workout_id = it,
                        weight = score.weight,
                        day_workout_id = score.dayWorkoutId
                    )
                }
            } else {
                queries.updateScore(
                    reps = score.reps,
                    weight = score.weight,
                    id = score.id
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