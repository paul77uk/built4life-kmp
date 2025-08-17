package paul.vickers.built4life.features.scores.model

import kotlinx.serialization.Serializable

@Serializable
data class Score(
    val id: Long? = null,
    val reps: Long?,
    val weight: Long?,
    val workoutId: Long?,
    val dayWorkoutId: Long?
)