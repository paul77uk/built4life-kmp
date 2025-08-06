package paul.vickers.built4life.ui.addScore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.ui.composables.B4LForm
import paul.vickers.built4life.ui.composables.B4LTopAppBar
import paul.vickers.built4life.utils.ScreenAction

@Composable
fun AddScoreScreen(
    onBackClick: () -> Unit
) {
    val viewModel = koinViewModel<AddScoreViewModel>()
    val repsValue by viewModel.repsInput.collectAsStateWithLifecycle()
    val weightValue by viewModel.weightInput.collectAsStateWithLifecycle()
//    val scores by viewModel.scores.collectAsStateWithLifecycle()
    val oneRepMax = viewModel.workoutOneRepMax
    val reps = viewModel.workoutReps
    val weight = viewModel.workoutWeight
    val title = viewModel.workoutTitle



    Column() {
        title?.let {
            B4LTopAppBar(
                title = it,
                onClick = { /*TODO*/ },
                onBackIconClick = { onBackClick() },
                screenAction = ScreenAction.ADD_SCORE,
            )
        }
        B4LForm(
            onSubmit = {
                viewModel.upsertScore()
                onBackClick()
            }
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    enabled = false,
                    modifier = Modifier.weight(1f),
                    label = { Text("Reps") },
                    readOnly = true,
                    value = reps.toString(),
                    onValueChange = {},
                )
                OutlinedTextField(
                    enabled = false,
                    modifier = Modifier.weight(1f),
                    label = { Text("Weight") },
                    readOnly = true,
                    value = "$weight KG", // TODO: implement previous score
                    onValueChange = {},
                )
                OutlinedTextField(
                    enabled = false,
                    modifier = Modifier.weight(1f),
                    label = { Text("1 Rep Max") },
                    readOnly = true,
                    value = "$oneRepMax KG", // TODO: implement previous score
                    onValueChange = {},
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    label = { Text("New Reps") },
                    placeholder = { Text("0") },
                    value = repsValue,
                    onValueChange = viewModel::onRepsTxtChange
                )

                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    label = { Text("New Weight") },
                    placeholder = { Text("0") },
                    value = weightValue,
                    onValueChange = viewModel::onWeightTxtChange
                )
            }
        }
    }
}