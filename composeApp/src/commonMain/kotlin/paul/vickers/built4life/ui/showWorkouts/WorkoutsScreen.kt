package paul.vickers.built4life.ui.showWorkouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.ui.composables.B4LTopAppBar
import paul.vickers.built4life.ui.showWorkouts.components.ScoreComponent
import paul.vickers.built4life.utils.ScreenAction

@Composable
fun WorkoutsScreen(
    onAddEditClick: (Long?) -> Unit,
    onLogScoreClick: (Long) -> Unit
) {
    val viewModel = koinViewModel<WorkoutsViewModel>()
    val workoutList by viewModel.workouts.collectAsStateWithLifecycle(initialValue = emptyList())

    Column(
    ) {
        B4LTopAppBar(
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
                key = { it.workout.id!! }) { workout ->
                OutlinedCard() {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            workout.workout.title.uppercase(),
                            modifier = Modifier.padding(16.dp),
                            fontWeight = FontWeight.Bold,
                        )
                        IconButton(
                            onClick = { onAddEditClick(workout.workout.id) }
                        ) {
                            Icon(
                                Icons.Outlined.Edit,
                                contentDescription = "Edit Workout",
                            )
                        }

                    }

                    if (workout.scores.isEmpty())
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                                .padding(bottom = 8.dp),
                            onClick = { onLogScoreClick(workout.workout.id!!) }
                        ) {
                            Text(
                                "LOG SCORE",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    else {
                        ScoreComponent(
                            scores = workout.scores,
                            onEditScoreClick = { onLogScoreClick(workout.workout.id!!) },
                            eliteLevel = workout.workout.eliteLevel
                        )
                    }
                }
            }
        }
    }
}

