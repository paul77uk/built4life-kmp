package paul.vickers.built4life.features.workouts.ui.showWorkouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

import paul.vickers.built4life.common_composables.B4LTopAppBar
import paul.vickers.built4life.features.workouts.model.Workout
import paul.vickers.built4life.features.workouts.model.WorkoutWithMaxScore
import paul.vickers.built4life.features.workouts.ui.showWorkouts.components.WorkoutCard

import paul.vickers.built4life.utils.ScreenAction

@Composable
fun WorkoutsScreen(
    viewModel: WorkoutsViewModel = koinViewModel(),
    onAddEditClick: (Workout?) -> Unit,
    onLogScoreClick: (WorkoutWithMaxScore?) -> Unit,
    navigateToProgramScreen: () -> Unit
) {
    val workoutList: List<WorkoutWithMaxScore?> by viewModel.workouts.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )

    Scaffold(

        topBar = {
            B4LTopAppBar(
                title = "Exercises",
                onClick = {
                    onAddEditClick(null)
                },
                screenAction = ScreenAction.JUST_ADD,
            )
        },
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {

                NavigationBarItem(
                    selected = false,
                    onClick = navigateToProgramScreen,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.FileCopy,
                            contentDescription = "Programs"

                        )
                    },
                    label = { Text("Programs") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.FitnessCenter,
                            contentDescription = "Exercises"

                        )
                    },
                    label = { Text("Exercises") }
                )

            }
        }
    )
    { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 300.dp),
            modifier = Modifier.padding(paddingValues).padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = workoutList,
                key = { workout -> workout?.id!! }
            ) { workout ->
                workout?.let {
                    WorkoutCard(
                        workout = workout,
                        onAddEditClick = {
                            onAddEditClick(
                                Workout(
                                    id = workout.id,
                                    title = workout.title,
                                )
                            )
                        },
                        onLogScoreClick = {
                            onLogScoreClick(
                                workout
                            )
                        }
                    )
                }
            }
        }
    }

}


