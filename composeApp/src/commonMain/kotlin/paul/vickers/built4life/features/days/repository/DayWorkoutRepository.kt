package paul.vickers.built4life.features.days.repository

import kotlinx.coroutines.flow.Flow
import paul.vickers.built4life.features.days.model.DayWorkout

interface DayWorkoutRepository {

    fun getDayWorkoutsByDayId(dayId: Long): Flow<List<DayWorkout>>

    suspend fun upsert(dayWorkout: DayWorkout)

    suspend fun delete(dayWorkout: DayWorkout)

}