package paul.vickers.built4life.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import paul.vickers.built4life.ui.addScore.AddScoreScreen
import paul.vickers.built4life.ui.showPrograms.ProgramsScreen
import paul.vickers.built4life.ui.showWorkouts.WorkoutsScreen
import paul.vickers.built4life.ui.upsertDay.UpsertDayScreen
import paul.vickers.built4life.ui.upsertProgram.UpsertProgramScreen
import paul.vickers.built4life.ui.upsertWorkout.UpsertWorkoutScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {

    NavHost(
        modifier = Modifier.padding(innerPadding)
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow).fillMaxSize(),
        navController = navController,
        startDestination = Routes.ProgramsScreen
    ) {
        composable<Routes.WorkoutsScreen> {
            WorkoutsScreen(
                onAddEditClick = { workout ->
                    navController.navigate(
                        Routes.UpsertWorkoutScreen(
                            workoutId = workout?.id,
                            workoutTitle = workout?.title,
                            creationDate = workout?.creationDate,
                        )
                    )
                },
                onLogScoreClick = { workout ->
                    navController.navigate(
                        Routes.AddScoreScreen(
                            workoutId = workout?.id,
                            workoutTitle = workout?.title,
                            workoutWeight = workout?.weight,
                            workoutReps = workout?.reps,
                            workoutOneRepMax = workout?.oneRepMax,
                        )
                    )
                },
            )
        }
        composable<Routes.UpsertWorkoutScreen> {
            UpsertWorkoutScreen(onBackClick = navController::navigateUp)
        }

        composable<Routes.AddScoreScreen> {
            AddScoreScreen(onBackClick = navController::navigateUp)
        }

        composable<Routes.ProgramsScreen> {
            ProgramsScreen(
                onAddEditClick = { program ->
                    navController.navigate(
                        Routes.UpsertProgramScreen(
                            programId = program?.id,
                            programTitle = program?.title
                        )
                    )
                },
                onDayUpsertScreenClick = { day ->
                    navController.navigate(Routes.UpsertDayScreen(
                        dayId = day?.id,
                        dayTitle = day?.title,
                        programId = day?.programId
                    ))
                }
            )
        }

        composable<Routes.UpsertProgramScreen> {
            UpsertProgramScreen(onBackClick = navController::navigateUp)
        }

        composable<Routes.UpsertDayScreen> {
            UpsertDayScreen(
                onBackClick = navController::navigateUp
            )
        }

    }
}