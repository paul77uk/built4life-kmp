package paul.vickers.built4life.ui.upsertWorkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.ui.composables.B4LForm
import paul.vickers.built4life.ui.composables.B4LTopAppBar
import paul.vickers.built4life.ui.deleteWorkouts.DeleteWorkoutDialog
import paul.vickers.built4life.ui.deleteWorkouts.DeleteWorkoutViewModel
import paul.vickers.built4life.utils.ScreenAction

@Composable
fun UpsertWorkoutScreen(
    onBackClick: () -> Unit,
) {
    val viewModel = koinViewModel<UpsertWorkoutViewModel>()
    val deleteDialog = koinViewModel<DeleteWorkoutViewModel>()
    val value by viewModel.workoutTitleInput.collectAsStateWithLifecycle()
    val eliteLevel by viewModel.eliteLevelInput.collectAsStateWithLifecycle()
    val workoutId = viewModel.workoutId

    Column(
    ) {
        B4LTopAppBar(
            title = if (workoutId != null) "Edit Workout" else "Add Workout",
            onClick = deleteDialog::openDeleteDialog,
            onBackIconClick = onBackClick,
            screenAction = if (workoutId != null) ScreenAction.EDIT_WORKOUT else ScreenAction.ADD_WORKOUT
        )
        B4LForm(
            onSubmit = {
                viewModel.upsertWorkout()
                onBackClick()
            }
        ) {
            OutlinedTextField(
                label = { Text("Workout Title") },
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = viewModel::onTxtChange
            )

            OutlinedTextField(
                label = { Text("Elite Level") },
                modifier = Modifier.fillMaxWidth(),
                value = eliteLevel,
                onValueChange = viewModel::onEliteLevelChange
            )
        }
        DeleteWorkoutDialog(onBackClick)
    }
}