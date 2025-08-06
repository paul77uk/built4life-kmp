package paul.vickers.built4life.repository

import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import paul.vickers.built4life.model.Workout
import paul.vickers.built4life.model.WorkoutWithMaxScore

class WebWorkoutRepositoryImpl(
    private val settings: Settings,
) : WorkoutRepository {

    companion object {
        private const val WORKOUT_KEY = "workouts"
    }

    private val json = Json { ignoreUnknownKeys = true }

    private fun loadWorkouts(): List<Workout> {
        val storedListInString = settings.getString(WORKOUT_KEY, "")
        return try {
            json.decodeFromString(storedListInString)
        } catch (e: Exception) {
            emptyList<Workout>()
        }
    }

    private suspend fun saveWorkout(workouts: List<Workout>) {
        withContext(Dispatchers.Default) {
            val encodedList = json.encodeToString(workouts)
            settings.putString(WORKOUT_KEY, encodedList)

        }
    }

    override val workouts: Flow<List<Workout>> = flowOf(loadWorkouts())
//    override val scoreWithWorkout: Flow<List<ScoreWithWorkout>>
//        get() = TODO("Not yet implemented")
//    override val workoutsWithScores: Flow<List<WorkoutsWithScores>>
//        get() = TODO("Not yet implemented")
    override val workoutsWithMaxScores: Flow<List<WorkoutWithMaxScore>>
        get() = TODO("Not yet implemented")

    override suspend fun getById(id: Long): Workout? {
        TODO("Not yet implemented")
    }

    override suspend fun upsert(workout: Workout) {
        if (workout.id == null) {
            insert(workout)
        } else {
            update(workout)
        }
    }

    suspend fun insert(workout: Workout) {
        val workouts = loadWorkouts().toMutableList()
        workouts.add(workout)
        saveWorkout(workouts)
    }

    suspend fun update(workout: Workout) {
        val workouts = loadWorkouts().toMutableList()
        val index = workouts.indexOfFirst { it.id == workout.id }
        if (index != -1) {
            workouts[index] = workout
            saveWorkout(workouts)
        }
    }

    override suspend fun delete(workoutId: Long) {
        val workouts = loadWorkouts().toMutableList()
        workouts.remove(workouts.find { it.id == workoutId })
        saveWorkout(workouts)
    }

    override suspend fun updateMaxScore(workoutId: Long, maxScore: Long) {
        TODO("Not yet implemented")
    }
}