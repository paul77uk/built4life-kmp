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
import paul.vickers.built4life.ui.composables.B4LTopAppBar
import paul.vickers.built4life.ui.showWorkouts.components.WorkoutCard
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
                WorkoutCard(
                    workout = workout,
                    onAddEditClick = { onAddEditClick(workout.workout.id) },
                    onLogScoreClick = { workout.workout.id?.let { onLogScoreClick(it) } }
                )
            }
        }
    }
}

