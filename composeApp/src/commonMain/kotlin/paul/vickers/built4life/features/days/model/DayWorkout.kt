package paul.vickers.built4life.features.days.model

import paul.vickers.built4life.features.scores.model.Score

data class DayWorkout(
    val id: Long? = null,
    val dayId: Long? = null,
    val workoutId: Long? = null,
    val workoutTitle: String? = null,
    val scores: List<Score> = emptyList(),
    val maxReps: List<Long?> = emptyList()
)
