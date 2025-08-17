package paul.vickers.built4life.features.programs.model

import kotlinx.serialization.Serializable

@Serializable
data class Program(
    val id: Long? = null,
    val title: String
)