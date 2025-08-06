package paul.vickers.built4life.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.model.Workout
import paul.vickers.built4life.model.WorkoutWithMaxScore

class WorkoutRepositoryImpl(
    private val b4lDatabase: B4LDatabase,
) : WorkoutRepository {

    private val queries = b4lDatabase.workoutQueries

    override val workouts: Flow<List<Workout>> =
        queries.getAll().asFlow().mapToList(
            Dispatchers.Default
        ).map { workoutEntities ->
            workoutEntities.map { workoutEntity ->
                Workout(
                    id = workoutEntity.id,
                    title = workoutEntity.title,
                    eliteLevel = workoutEntity.elite_level,
                    creationDate = workoutEntity.creation_date,
                )
            }
        }
//    override val scoreWithWorkout: Flow<List<ScoreWithWorkout>> =
//        queries.getAllWorkoutsWithScores().asFlow().mapToList(
//            Dispatchers.Default
//        ).map { workoutEntities ->
//            workoutEntities.map { workoutEntity ->
//                ScoreWithWorkout(
//                    scoreId = workoutEntity.id_,
//                    workoutId = workoutEntity.id,
//                    workoutTitle = workoutEntity.title,
//                    workoutWeight = workoutEntity.weight,
//                    workoutEliteLevel = workoutEntity.eliteLevel,
//                    scoreReps = workoutEntity.reps,
//                )
//            }
//        }

//    override val workoutsWithScores: Flow<List<WorkoutsWithScores>> =
//        queries.getAllWorkoutsWithScores().asFlow().mapToList(
//            Dispatchers.Default
//        ).map { workoutEntities ->
//            workoutEntities.groupBy { it.id }.map { (workoutId, values) ->
//                WorkoutsWithScores(
//                    workout = Workout(
//                        id = workoutId,
//                        title = values.first().title,
//                        weight = values.first().weight,
//                        eliteLevel = values.first().eliteLevel,
//                        maxScore = values.first().maxScore,
//                        creationDate = values.first().creationDate
//                    ),
//                    scores = values.filterNot { it.id_ == null }.map { scoreEntity ->
//                        Score(
//                            id = scoreEntity.id_,
//                            reps = scoreEntity.reps!!,
//                            workoutId = scoreEntity.id,
//                        )
//                    }
//                )
//            }
//        }
    override val workoutsWithMaxScores: Flow<List<WorkoutWithMaxScore>> =
        queries.getAllWorkoutsWithMaxScore().asFlow().mapToList(
            Dispatchers.Default
        ).map { workoutEntities ->
            workoutEntities.map { workoutEntity ->
                WorkoutWithMaxScore(
                    id = workoutEntity.id,
                    title = workoutEntity.title,
                    weight = workoutEntity.weight,
                    reps = workoutEntity.reps,
                    oneRepMax = workoutEntity.one_rep_max,
                    level = workoutEntity.level,
                    progress = workoutEntity.progress
                )
            }
        }


//        return withContext(Dispatchers.Default) {
//            queries.getAll().executeAsList().map { row ->
//                Workout(
//                    id = row.id,
//                    title = row.title,
//                )
//            }
//        }


    override suspend fun getById(id: Long): Workout? {
        return withContext(Dispatchers.Default) {
            queries.getById(id).executeAsOneOrNull()?.let { workoutEntity ->
                Workout(
                    id = workoutEntity.id,
                    title = workoutEntity.title,
                    eliteLevel = workoutEntity.elite_level,
                    creationDate = workoutEntity.creation_date
                )
            }
        }
    }

    override suspend fun upsert(workout: Workout) {
        withContext(Dispatchers.Default) {
            queries.upsert(
                id = workout.id,
                title = workout.title,
                elite_level = workout.eliteLevel,
            )
        }
    }

    override suspend fun delete(workoutId: Long) {
        withContext(Dispatchers.Default) {
            queries.delete(workoutId)
        }
    }

    override suspend fun updateMaxScore(workoutId: Long, maxScore: Long) {
//        withContext(Dispatchers.Default) {
//            queries.updateMaxScoreById(maxScore, workoutId)
//        }
    }
}