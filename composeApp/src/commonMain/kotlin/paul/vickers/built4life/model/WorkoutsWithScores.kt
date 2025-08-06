package paul.vickers.built4life.model

data class WorkoutsWithScores(
    val workout: Workout,
    val scores: List<Score>? = emptyList()
)
