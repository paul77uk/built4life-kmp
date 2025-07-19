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
import paul.vickers.built4life.ui.showWorkouts.WorkoutsScreen
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
        startDestination = Routes.WorkoutsScreen
    ) {
        composable<Routes.WorkoutsScreen> {
            WorkoutsScreen(
                onAddEditClick = { workoutId ->
                    navController.navigate(Routes.UpsertWorkoutScreen(workoutId = workoutId))
                },
                onLogScoreClick = { workoutId ->
                    navController.navigate(Routes.AddScoreScreen(workoutId = workoutId))
                },
            )
        }
        composable<Routes.UpsertWorkoutScreen> {
            UpsertWorkoutScreen(onBackClick = navController::navigateUp)
        }

        composable<Routes.AddScoreScreen> {
            AddScoreScreen(onBackClick = navController::navigateUp)
        }
    }
}