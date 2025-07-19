package paul.vickers.built4life.model

import kotlinx.serialization.Serializable

@Serializable
data class Score(
    val id: Long? = null,
    val reps: Long,
    val workoutId: Long?,
)