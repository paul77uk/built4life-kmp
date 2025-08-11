package paul.vickers.built4life.ui.upsertDay

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.ui.composables.B4LForm
import paul.vickers.built4life.ui.composables.B4LTopAppBar
import paul.vickers.built4life.utils.ScreenAction

@Composable
fun UpsertDayScreen(
    onBackClick: () -> Unit,
    viewModel: UpsertDayViewModel = koinViewModel()
) {
    val value by viewModel.dayTitleInput.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            B4LTopAppBar(
                title = "Upsert Day",
                onClick = {},
                onBackIconClick = onBackClick,
                screenAction = ScreenAction.ADD_WORKOUT
            )
        }) {
        B4LForm(
            modifier = Modifier.padding(it),
            onSubmit = {
                viewModel.upsertDay()
                onBackClick()
            }
        ) {
            OutlinedTextField(
                label = { Text("Day Title") },
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = viewModel::onTxtChange
            )
        }
    }
}
