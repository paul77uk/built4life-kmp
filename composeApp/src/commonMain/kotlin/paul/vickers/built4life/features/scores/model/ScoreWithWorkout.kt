package paul.vickers.built4life.features.scores.model

data class ScoreWithWorkout(
    val scoreId: Long?,
    val workoutId: Long,
    val workoutTitle: String,
    val scoreReps: Long?,
    val workoutWeight: Long?,
    val workoutEliteLevel: Long?
)