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
    val value by viewModel.scoreTitleInput.collectAsStateWithLifecycle()
    val scores by viewModel.scores.collectAsStateWithLifecycle()

    Column() {
        B4LTopAppBar(
            onClick = { /*TODO*/ },
            onBackIconClick = onBackClick,
            screenAction = ScreenAction.ADD_SCORE,
        )
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
                val maxScore = scores.maxByOrNull { it.reps }?.reps ?: 0
                OutlinedTextField(
                    enabled = false,
                    modifier = Modifier.weight(1f),
                    label = { Text("Max") },
                    readOnly = true,
                    value = "$maxScore",
                    onValueChange = {},
                )
                OutlinedTextField(
                    enabled = false,
                    modifier = Modifier.weight(1f),
                    label = { Text("Previous") },
                    readOnly = true,
                    value = "${scores.lastOrNull()?.reps ?: 0}",
                    onValueChange = {},
                )
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    label = { Text("New") },
                    placeholder = { Text("0") },
                    value = value,
                    onValueChange = viewModel::onTxtChange
                )
            }
        }
    }
}