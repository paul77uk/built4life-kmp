package paul.vickers.built4life.model

data class WorkoutWithMaxScore(
    val id: Long?,
    val title: String,
    val weight: Long?,
    val reps: Long?,
    val oneRepMax: Long?,
    val level: String? = null,
    val progress: Double? = null
)
