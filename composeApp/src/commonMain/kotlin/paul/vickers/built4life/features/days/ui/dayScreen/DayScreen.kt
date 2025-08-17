package paul.vickers.built4life.features.days.ui.dayScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.common_composables.B4LTopAppBar
import paul.vickers.built4life.utils.ScreenAction
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import paul.vickers.built4life.features.days.model.DayWorkout
import paul.vickers.built4life.features.scores.model.Score
import paul.vickers.built4life.features.workouts.model.Workout


@Composable
fun DaySreen(
    onBackClick: () -> Unit,
    viewModel: DayScreenViewModel = koinViewModel()
) {
    val dayTitle = viewModel.dayTitle


    val dayWorkouts: List<DayWorkout> by viewModel.dayWorkouts.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )
    val workouts: List<Workout?> by viewModel.workouts.collectAsStateWithLifecycle(initialValue = emptyList())

    val repsInput: String by viewModel.repsInput.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            B4LTopAppBar(
                title = dayTitle ?: "",
                onClick = { },
                onBackIconClick = onBackClick,
                screenAction = ScreenAction.ADD_AND_BACK
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                AddExerciseDropdown(
                    workouts = workouts,
                    onUpsertExerciseClick = {
                        viewModel.upsertDayWorkout(
                            DayWorkout(
                                workoutId = it?.id,
                                workoutTitle = it?.title
                            )

                        )
                    }
                )
            }
            items(dayWorkouts, key = { it.id!! }) { dayWorkout ->
                OutlinedCard {
                    ListItem(
                        headlineContent = { Text(dayWorkout.workoutTitle ?: "") },
                        trailingContent = {
                            WorkoutItem(
                                onDeleteClick = {
                                    viewModel.deleteDayWorkout(dayWorkout)
                                },
                                workouts = workouts,
                                onUpsertExerciseClick = {
                                    viewModel.upsertDayWorkout(
                                        DayWorkout(
                                            id = dayWorkout.id,
                                            workoutId = it?.id,
                                            workoutTitle = it?.title
                                        )
                                    )
                                }
                            )
                        }
                    )
                }
                dayWorkout.scores.forEachIndexed { index, score ->
                    ScoreItem(
                        score,
                        index,
//                        value = repsInput,
//                        onValueChange = {
//                            viewModel.onRepsTxtChange(it)
//                        },
                        onUpdateClick = { reps, weight ->
                            viewModel.updateScore(
                                Score(
                                    id = score.id,
                                    reps = reps.toLong(),
                                    weight = weight.toLong(),
                                    workoutId = score.workoutId,
                                    dayWorkoutId = score.dayWorkoutId
                                )

                            )
                        }
                    )
//                            ListItem(
//                                headlineContent = { Text("set: ${index + 1} id: ${score.id} reps: ${score.reps} weight: ${score.weight}") },
//                                trailingContent = {
//                                    Button(onClick = {
//                                        viewModel.updateScore(
//                                            score
//                                        )
//                                    }) {
//                                        Text("Update")
//
//                                    }
//                                },
//
//                                )

                }

                Button(onClick = {
                    viewModel.addSet(dayWorkout.workoutId!!, dayWorkout.id)
                }) {
                    Text("Add Set")
                }
            }
        }
    }
}

@Composable
fun WorkoutItem(
    onDeleteClick: () -> Unit,
    workouts: List<Workout?>,
    onUpsertExerciseClick: (Workout?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }

    Box(
    ) {
        Icon(
            Icons.Default.MoreVert, contentDescription = "More options",
            modifier = Modifier.clip(
                CircleShape
            ).clickable { expanded = !expanded }.padding(vertical = 4.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Edit"
                    )
                },
                text = {
                    Box(
                    ) {
                        Text("Edit")
                        DropdownMenu(
                            expanded = expanded2,
                            onDismissRequest = { expanded2 = false }
                        ) {
                            workouts.forEach { workout ->
                                DropdownMenuItem(
                                    text = { Text(workout?.title ?: "") },
                                    onClick = {
                                        onUpsertExerciseClick(workout)
                                        expanded2 = false
                                        expanded = false

                                    }
                                )
                            }
                        }
                    }
                },
                onClick = { expanded2 = !expanded2 }
            )
            HorizontalDivider()
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete"
                    )
                },
                text = { Text("Delete") },
                onClick = {
                    expanded = false
                    onDeleteClick()
                }
            )
        }
    }
}

@Composable
fun AddExerciseDropdown(
    workouts: List<Workout?>,
    onUpsertExerciseClick: (Workout?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
    ) {
        Button(onClick = { expanded = !expanded }) {
            Text("Add Exercise")

        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            workouts.forEach { workout ->
                DropdownMenuItem(
                    text = { Text(workout?.title ?: "") },
                    onClick = {
                        onUpsertExerciseClick(workout)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ScoreItem(
    score: Score,
    index: Int,
//    value: String,
//    onValueChange: (String) -> Unit,
    onUpdateClick: (String, String) -> Unit
) {
    var repsValue: String by remember { mutableStateOf(score.reps.toString()) }
    var weightValue: String by remember { mutableStateOf(score.weight.toString()) }

    if (score.reps != null && score.weight != null)
        Row {
            OutlinedCard() {
                Text(
                    "${index + 1} "
                )
            }
            OutlinedTextField(
                value = repsValue,
                onValueChange = {
                    repsValue = it
                },
            )
            OutlinedTextField(
                value = weightValue,
                onValueChange = {
                    weightValue = it
                },
            )
            Button(
                onClick = {
                    onUpdateClick(repsValue, weightValue)
                }
            ) {
                Text("Update")

            }
        }
}