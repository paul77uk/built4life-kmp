package paul.vickers.built4life.features.days.ui.dayScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import paul.vickers.built4life.features.days.model.DayWorkout
import paul.vickers.built4life.features.scores.model.Score
import paul.vickers.built4life.features.workouts.model.Workout


@Composable
fun DayScreen(
    onBackClick: () -> Unit,
    viewModel: DayScreenViewModel = koinViewModel()
) {
    val dayTitle = viewModel.dayTitle


    val dayWorkouts: List<DayWorkout> by viewModel.dayWorkouts.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )
    val workouts: List<Workout?> by viewModel.workouts.collectAsStateWithLifecycle(initialValue = emptyList())

    val repsInput: String by viewModel.repsInput.collectAsStateWithLifecycle()
    val weightInput: String by viewModel.weightInput.collectAsStateWithLifecycle()



    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
            modifier = Modifier.padding(paddingValues).padding(16.dp).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                OutlinedCard(
                    modifier = Modifier.width(400.dp)
                ) {
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
                Row(
                    modifier = Modifier.width(400.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Sets", modifier = Modifier.weight(1f))
                    Text("Max", modifier = Modifier.weight(1f))
                    Text("Reps", modifier = Modifier.weight(1f))
                    Text("Weight", modifier = Modifier.weight(1f))
                    Text("", modifier = Modifier.weight(1f))
                }
                dayWorkout.scores.withIndex().zip(dayWorkout.maxReps) { (index, score), maxReps ->
                    ScoreItem(
                        maxReps = maxReps,
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
                        },
                        onDelete = {
                            viewModel.deleteScore(it)

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
    maxReps: Long?,
    score: Score,
    index: Int,
//    value: String,
//    onValueChange: (String) -> Unit,
    onDelete: (Score) -> Unit,
    onUpdateClick: (String, String) -> Unit
) {
    var repsValue: String by remember { mutableStateOf(score.reps.toString()) }
    var weightValue: String by remember { mutableStateOf(score.weight.toString()) }
    var checked: Boolean by remember { mutableStateOf(false) }

    if (score.reps != null && score.weight != null)
        Row(
            modifier = Modifier.width(400.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                "${index + 1} ", modifier = Modifier.weight(1f), fontSize = 14.sp
            )

            Text(
                "$maxReps", modifier = Modifier.weight(1f), fontSize = 14.sp
            )

            BasicTextField(
                value = repsValue,
                onValueChange = {
                    repsValue = it
                }, modifier = Modifier.weight(1f)
            )
            BasicTextField(
                value = weightValue,
                onValueChange = {
                    weightValue = it
                }, modifier = Modifier.weight(1f)
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        if (it) {
                            onUpdateClick(repsValue, weightValue)
                        }
                    }, modifier = Modifier.weight(1f)
                )
            IconButton(
                onClick = { onDelete(score) }, modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete"
                )
            }
            }
        }
}