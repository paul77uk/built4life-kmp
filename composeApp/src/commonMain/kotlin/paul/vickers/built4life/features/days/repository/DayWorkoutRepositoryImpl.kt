package paul.vickers.built4life.features.days.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import paul.vickers.built4life.B4LDatabase
import paul.vickers.built4life.features.days.model.DayWorkout
import paul.vickers.built4life.features.scores.model.Score

class DayWorkoutRepositoryImpl(
    private val b4lDatabase: B4LDatabase,
) : DayWorkoutRepository {

    private val queries = b4lDatabase.dayWorkoutQueries



    override fun getDayWorkoutsByDayId(dayId: Long): Flow<List<DayWorkout>> {
        return queries.getDayWorkoutsByDayId(dayId).asFlow().mapToList(
            Dispatchers.Default
        ).map { dayWorkoutEntities ->
            dayWorkoutEntities.groupBy { it.id }.map { dayWorkout ->
                DayWorkout(
                    id = dayWorkout.value.first().id,
                    dayId = dayWorkout.value.first().day_id,
                    workoutId = dayWorkout.value.first().workout_id,
                    workoutTitle = dayWorkout.value.first().title,
                    scores = dayWorkout.value.map {
                        Score(
                            id = it.score_id,
                            reps = it.reps,
                            weight = it.weight,
                            workoutId = it.workout_id,
                            dayWorkoutId = it.id
                        )
                    }
                ) }
        }
    }

    override suspend fun upsert(dayWorkout: DayWorkout) {
        if (dayWorkout.id == null) {
            queries.insert(
                day_id = dayWorkout.dayId,
                workout_id = dayWorkout.workoutId
            )
        } else {
            queries.update(
                id = dayWorkout.id,
                workout_id = dayWorkout.workoutId
            )
        }
    }

    override suspend fun delete(dayWorkout: DayWorkout) {
        queries.delete(dayWorkout.id!!)
    }


}