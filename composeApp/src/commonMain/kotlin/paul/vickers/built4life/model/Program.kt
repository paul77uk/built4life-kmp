package paul.vickers.built4life.model

import kotlinx.serialization.Serializable

@Serializable
data class Program(
    val id: Long? = null,
    val title: String
)