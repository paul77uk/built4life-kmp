package paul.vickers.built4life.features.workouts.model

import kotlinx.serialization.Serializable

@Serializable
data class Workout(
    val id: Long?,
    val title: String,
    val eliteLevel: Long? = null,
    val creationDate: String? = null
)