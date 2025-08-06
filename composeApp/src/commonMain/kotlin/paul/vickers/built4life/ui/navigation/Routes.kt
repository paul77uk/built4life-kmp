package paul.vickers.built4life.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object WorkoutsScreen : Routes()

    @Serializable
    data class UpsertWorkoutScreen(
        val workoutId: Long?,
        val workoutTitle: String? = null,
        val creationDate: String?,
    ) :
        Routes()

    @Serializable
    data class AddScoreScreen(
        val workoutId: Long?,
        val workoutTitle: String? = null,
        val workoutWeight: Long? = null,
        val workoutReps: Long? = null,
        val workoutOneRepMax: Long? = null,
    ) : Routes()

}