package paul.vickers.built4life.model

import kotlinx.serialization.Serializable

@Serializable
data class Workout(
    val id: Long? = null,
    val title: String,
    val weight: Long?,
    val eliteLevel: Long?,
)