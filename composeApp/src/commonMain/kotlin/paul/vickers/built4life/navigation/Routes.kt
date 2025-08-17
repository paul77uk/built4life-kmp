package paul.vickers.built4life.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object WorkoutsScreen : Routes()

    @Serializable
    data class UpsertWorkoutScreen(
        val workoutId: Long? = null,
        val workoutTitle: String? = null,
        val creationDate: String? = null
    ) :
        Routes()

    @Serializable
    data class AddScoreScreen(
        val workoutId: Long?,
        val workoutTitle: String? = null,
        val workoutWeight: Long? = null,
        val workoutReps: Long? = null,
        val workoutOneRepMax: Long? = null
    ) : Routes()

    @Serializable
    data object ProgramsScreen : Routes()

    @Serializable
    data class UpsertProgramScreen(
        val programId: Long? = null,
        val programTitle: String? = null
    ) : Routes()

    @Serializable
    data class UpsertDayScreen(
        val dayId: Long? = null,
        val dayTitle: String? = null,
        val programId: Long? = null
    ) : Routes()

    @Serializable
    data class DayScreen(
        val dayId: Long,
        val dayTitle: String? = null
    ) : Routes()


}