package paul.vickers.built4life.repository


import kotlinx.coroutines.flow.Flow
import paul.vickers.built4life.model.ScoreWithWorkout
import paul.vickers.built4life.model.Workout
import paul.vickers.built4life.model.WorkoutsWithScores

interface WorkoutRepository {

    val workouts: Flow<List<Workout>>
    val scoreWithWorkout: Flow<List<ScoreWithWorkout>>
    val workoutsWithScores: Flow<List<WorkoutsWithScores>>
    suspend fun getById(id: Long): Workout?
    suspend fun upsert(workout: Workout)
    suspend fun delete(workout: Workout)
}