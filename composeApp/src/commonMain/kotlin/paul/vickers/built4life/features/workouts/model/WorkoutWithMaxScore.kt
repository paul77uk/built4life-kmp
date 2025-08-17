package paul.vickers.built4life.features.workouts.model

data class WorkoutWithMaxScore(
    val id: Long?,
    val title: String,
    val weight: Long?,
    val reps: Long?,
    val oneRepMax: Long?,
    val level: String? = null,
    val progress: Double? = null
)