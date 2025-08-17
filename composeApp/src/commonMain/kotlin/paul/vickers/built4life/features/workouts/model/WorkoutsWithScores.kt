package paul.vickers.built4life.features.workouts.model

import paul.vickers.built4life.features.scores.model.Score

data class WorkoutsWithScores(
    val workout: Workout,
    val scores: List<Score>? = emptyList()
)