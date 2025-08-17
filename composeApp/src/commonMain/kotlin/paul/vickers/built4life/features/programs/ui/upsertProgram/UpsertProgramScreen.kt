package paul.vickers.built4life.features.programs.ui.upsertProgram

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.common_composables.B4LForm
import paul.vickers.built4life.common_composables.B4LTopAppBar
import paul.vickers.built4life.utils.ScreenAction

@Composable
fun UpsertProgramScreen(
    viewModel: UpsertProgramViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val value by viewModel.programTitleInput.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            B4LTopAppBar(
                title = "Upsert Program",
                onClick = { /*TODO*/ },
                onBackIconClick = { onBackClick() },
                screenAction = ScreenAction.JUST_BACK,
            )
        }
    ) { paddingValues ->
        B4LForm(
            modifier = Modifier.padding(paddingValues),
            onSubmit = {
                viewModel.upsertProgram()
                onBackClick()
            }
        ) {
            OutlinedTextField(
                label = { Text("Program Title") },
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = viewModel::onTxtChange
            )
        }
    }
}
