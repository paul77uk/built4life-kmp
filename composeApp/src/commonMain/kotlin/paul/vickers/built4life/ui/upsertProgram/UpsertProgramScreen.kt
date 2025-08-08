package paul.vickers.built4life.ui.upsertProgram

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import paul.vickers.built4life.ui.composables.B4LForm
import paul.vickers.built4life.ui.composables.B4LTopAppBar
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
                screenAction = ScreenAction.ADD_WORKOUT,
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
