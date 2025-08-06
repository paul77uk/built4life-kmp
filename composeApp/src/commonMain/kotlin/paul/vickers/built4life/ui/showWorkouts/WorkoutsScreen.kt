package paul.vickers.built4life.ui.showWorkouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.model.Workout
import paul.vickers.built4life.model.WorkoutWithMaxScore
import paul.vickers.built4life.ui.composables.B4LTopAppBar
import paul.vickers.built4life.ui.showWorkouts.components.WorkoutCard
import paul.vickers.built4life.utils.ScreenAction

@Composable
fun WorkoutsScreen(
    viewModel: WorkoutsViewModel = koinViewModel(),
    onAddEditClick: (Workout?) -> Unit,
    onLogScoreClick: (WorkoutWithMaxScore?) -> Unit
) {
    val workoutList: List<WorkoutWithMaxScore?> by viewModel.workouts.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )
//    val maxScore by viewModel.maxScore.collectAsStateWithLifecycle()

    Column(
    ) {
        B4LTopAppBar(
            title = "Built4Life",
            onClick = {
                onAddEditClick(null)
            },
            screenAction = ScreenAction.HOME,
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 300.dp),
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = workoutList,
            ) { workout ->
                workout?.let {
                    WorkoutCard(
                        title = it.title,
                        reps = it.reps,
                        weight = it.weight,
                        oneRepMax = it.oneRepMax,
                        level = it.level,
                        progress = it.progress,
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
                                WorkoutWithMaxScore(
                                    id = workout.id,
                                    title = workout.title,
                                    weight = workout.weight,
                                    reps = workout.reps,
                                    oneRepMax = workout.oneRepMax,
                                )
                            )
                        }
                    )
                }
//                WorkoutCard(
//                    maxScore = workout.workout.maxScore,
//                    workout = workout,
//                    onAddEditClick = {
//                        onAddEditClick(
//                            Workout(
//                                id = workout.workout.id,
//                                title = workout.workout.title,
//                                weight = workout.workout.weight,
//                                eliteLevel = workout.workout.eliteLevel,
//                                maxScore = workout.workout.maxScore,
//                                creationDate = workout.workout.creationDate
//                            )
//                        )
//                    },
//                    onLogScoreClick = {
//                        onLogScoreClick(
//                            Workout(
//                                id = workout.workout.id,
//                                title = workout.workout.title,
//                                weight = workout.workout.weight,
//                                eliteLevel = workout.workout.eliteLevel,
//                                maxScore = workout.workout.maxScore,
//                                creationDate = workout.workout.creationDate
//                            )
//                        )
//                    }
//                )
            }
        }
    }
}


