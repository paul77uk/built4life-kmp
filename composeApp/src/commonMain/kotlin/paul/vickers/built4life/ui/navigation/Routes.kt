package paul.vickers.built4life.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object WorkoutsScreen : Routes()

    @Serializable
    data class UpsertWorkoutScreen(val workoutId: Long? = null) :
        Routes()

    @Serializable
    data class AddScoreScreen(val workoutId: Long? = null) : Routes()

}