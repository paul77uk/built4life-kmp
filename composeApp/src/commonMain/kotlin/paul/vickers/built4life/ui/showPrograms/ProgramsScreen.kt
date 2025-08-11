package paul.vickers.built4life.ui.showPrograms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.model.Day
import paul.vickers.built4life.model.Program
import paul.vickers.built4life.model.ProgramsWithDays
import paul.vickers.built4life.ui.composables.B4LTopAppBar
import paul.vickers.built4life.ui.composables.DeleteDialog
import paul.vickers.built4life.utils.ScreenAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramsScreen(
    viewModel: ProgramsViewModel = koinViewModel(),
    onAddEditClick: (Program?) -> Unit,
    onDayUpsertScreenClick: (Day?) -> Unit
) {
    val programs: List<ProgramsWithDays> by viewModel.programs.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )
//    val days: List<Day> by viewModel.days.collectAsStateWithLifecycle()

//    val showDays by viewModel.showDays.collectAsStateWithLifecycle()

    val showDialog by viewModel.showDeleteDialog.collectAsStateWithLifecycle()
    val programToDelete: Program? by viewModel.programToDelete.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            B4LTopAppBar(
                title = "Programs",
                onClick = { onAddEditClick(null) },
                screenAction = ScreenAction.HOME
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(programs, key = { it.program.id!! }) { program ->
                ProgramItem(
                    program = program,
//                    showDays = showDays,
                    onProgramEditClick = {
                        onAddEditClick(program.program)
                    },
//                    onToggleDays = { viewModel.toggleShowDays(program) },
                    onProgramDeleteClick = {

                        viewModel.openDeleteDialog()
                    },
                    onToggleDays = {

                    },
                    onDayUpsertScreenClick = {
                        onDayUpsertScreenClick(
                            Day(
                                id = null,
                                title = "",
                                programId = program.program.id!!

                            )
                        )
                    }
                )


                    DeleteDialog(
                        title = program.program.title,
                        showDialog = showDialog,
                        onDismiss = viewModel::dismissDeleteDialog,
                        onConfirm = {
                            viewModel.deleteProgram(program.program)
                        }
                    )


            }

        }
    }

}

@Composable
fun ProgramItem(
    program: ProgramsWithDays,
//    showDays: Boolean,
    onToggleDays: () -> Unit,
    onProgramEditClick: () -> Unit,
    onProgramDeleteClick: () -> Unit,
    onDayUpsertScreenClick: () -> Unit
) {
    var showDays: Boolean by rememberSaveable { mutableStateOf(false) }
    OutlinedCard {
        ListItem(
            headlineContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = program.program.title,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge
                    )
                    IconButton(
                        onClick = {
                            showDays = !showDays
                            if (showDays)
                                onToggleDays()
                        },
                    ) {
                        Icon(
                            modifier = Modifier.rotate(if (showDays) 90f else 0f),
                            imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                            contentDescription = "Arrow Right"
                        )
                    }
                }
            },
            trailingContent = {
                Row {
                    OutlinedIconButton(
                        shape = MaterialTheme.shapes.medium,
                        onClick = onDayUpsertScreenClick,
                    ) {
                        Icon(Icons.Outlined.Add, contentDescription = "Add")
                    }
                    OutlinedIconButton(
                        shape = MaterialTheme.shapes.medium,
                        onClick = onProgramEditClick,

                        ) {
                        Icon(Icons.Outlined.Edit, contentDescription = "Edit")
                    }
                    OutlinedIconButton(
                        shape = MaterialTheme.shapes.medium,
                        onClick = onProgramDeleteClick,
                    ) {
                        Icon(Icons.Outlined.Delete, contentDescription = "Delete")
                    }
                }
            },
            supportingContent = {

                Column {
                    program.days?.let { days ->
                        if (showDays) {
                            days.forEach { day ->
                                Text(
                                    text = day.title,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }
                }


            }
        )
    }
}