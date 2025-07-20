package paul.vickers.built4life.ui.showWorkouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.model.Score
import paul.vickers.built4life.ui.composables.B4LTopAppBar
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
                            onEditScoreClick = { onLogScoreClick(workout.workout.id!!) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreComponent(
    scores: List<Score>,
    onEditScoreClick: () -> Unit,
) {
    Spacer(
        modifier = Modifier.padding(top = 8.dp)
    )
    HorizontalDivider()
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${scores.maxByOrNull { it.reps }?.reps ?: 0}", color = Color(0xff397DD1), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(start = 4.dp))
            Text(
                "REPS",
                color = Color(0xff397DD1),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        IconButton(
            onClick = { onEditScoreClick() }
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit Score",
            )
        }
    }
}
