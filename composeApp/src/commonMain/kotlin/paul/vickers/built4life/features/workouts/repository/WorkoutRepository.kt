package paul.vickers.built4life.features.workouts.repository

import kotlinx.coroutines.flow.Flow
import paul.vickers.built4life.features.workouts.model.Workout
import paul.vickers.built4life.features.workouts.model.WorkoutWithMaxScore


interface WorkoutRepository {

    val workouts: Flow<List<Workout>>
//    val scoreWithWorkout: Flow<List<ScoreWithWorkout>>
//    val workoutsWithScores: Flow<List<WorkoutsWithScores>>

    val workoutsWithMaxScores: Flow<List<WorkoutWithMaxScore>>
    suspend fun getById(id: Long): Workout?
    suspend fun upsert(workout: Workout)
    suspend fun delete(workoutId: Long)
    suspend fun updateMaxScore(workoutId: Long, maxScore: Long)

}