package paul.vickers.built4life.features.programs.ui.showPrograms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.Attachment
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material.icons.outlined.FileOpen
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SportsBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.ComposeNavigator
import org.koin.compose.viewmodel.koinViewModel
import paul.vickers.built4life.features.days.model.Day
import paul.vickers.built4life.features.programs.model.Program
import paul.vickers.built4life.features.programs.model.ProgramsWithDays
import paul.vickers.built4life.common_composables.B4LTopAppBar
import paul.vickers.built4life.common_composables.DeleteDialog
import paul.vickers.built4life.utils.ScreenAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramsScreen(
    viewModel: ProgramsViewModel = koinViewModel(),
    onAddEditClick: (Program?) -> Unit,
    onDayUpsertScreenClick: (Day?) -> Unit,
    navigateToDayScreen: (Day) -> Unit,
    navigateToExerciseScreen: () -> Unit
) {
    val programs: List<ProgramsWithDays> by viewModel.programs.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )

    val showProgramDeleteDialog by viewModel.showProgramDeleteDialog.collectAsStateWithLifecycle()
    val showDayDeleteDialog by viewModel.showDayDeleteDialog.collectAsStateWithLifecycle()
    val programTitle: Program? by viewModel.programToDelete.collectAsStateWithLifecycle()
    val dayToDelete: Day? by viewModel.dayToDelete.collectAsStateWithLifecycle() // New

    Scaffold(
        topBar = {
            B4LTopAppBar(
                title = "Programs",
                onClick = { onAddEditClick(null) },
                screenAction = ScreenAction.JUST_ADD
            )
        },
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {

                    NavigationBarItem(
                        selected = false,
                        onClick = {
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.FileCopy,
                                contentDescription = "Programs"

                            )
                        },
                        label = { Text("Programs") }
                    )
                NavigationBarItem(
                    selected = false,
                    onClick = navigateToExerciseScreen,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.FitnessCenter,
                            contentDescription = "Exercises"

                        )
                    },
                    label = { Text("Exercises") }
                )

            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(programs, key = { it.program.id!! }) { program ->
                ProgramItem(
                    program = program,
                    onProgramEditClick = {
                        onAddEditClick(program.program)
                    },
                    onProgramDeleteClick = {
                        viewModel.openProgramDeleteDialog(program.program)
                    },
                    onDayUpsertScreenClick = onDayUpsertScreenClick,
                    onDayDeleteRequest = { day -> // New lambda
                        viewModel.openDayDeleteDialog(day)
                    },
                    navigateToDayScreen = navigateToDayScreen
                )

            }

        }

        programTitle?.title?.let {
            DeleteDialog(
                title = it,
                showDialog = showProgramDeleteDialog,
                onDismiss = viewModel::dismissProgramDeleteDialog,
                onConfirm = {
                    viewModel.deleteProgram()
                }
            )
        }

        dayToDelete?.title?.let { title ->
            DeleteDialog(
                title = title,
                showDialog = showDayDeleteDialog,
                onDismiss = viewModel::dismissDayDeleteDialog,
                onConfirm = {
                    viewModel.deleteSelectedDay()
                }
            )
        }
    }

}

@Composable
fun ProgramItem(
    program: ProgramsWithDays,
    onProgramEditClick: () -> Unit,
    onProgramDeleteClick: () -> Unit,
    onDayUpsertScreenClick: (Day?) -> Unit,
    onDayDeleteRequest: (Day) -> Unit,
    navigateToDayScreen: (Day) -> Unit
) {
    var showDays: Boolean by rememberSaveable { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    OutlinedCard(modifier = Modifier.width(350.dp)) {
        ListItem(
            headlineContent = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
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
                            },
                        ) {
                            Icon(
                                modifier = Modifier.rotate(if (showDays) 90f else 0f),
                                imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                                contentDescription = "Arrow Right"
                            )
                        }
                    }
                    Box(
                    ) {
                        Icon(
                            Icons.Default.MoreVert, contentDescription = "More options",
                            modifier = Modifier.clip(
                                CircleShape
                            ).clickable { expanded = !expanded }.padding(vertical = 4.dp)
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Edit,
                                        contentDescription = "Edit"
                                    )
                                },
                                text = { Text("Edit") },
                                onClick = onProgramEditClick
                            )
                            HorizontalDivider()
                            DropdownMenuItem(
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Delete"
                                    )
                                },
                                text = { Text("Delete") },
                                onClick = {
                                    expanded = false
                                    onProgramDeleteClick()
                                }
                            )
                        }
                    }
                }
            },
            supportingContent = {
                if (showDays)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            shape = MaterialTheme.shapes.medium,
                            onClick = {
                                onDayUpsertScreenClick(
                                    Day(
                                        title = null,
                                        programId = program.program.id
                                    )
                                )
                            }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("DAYS")
                                Icon(Icons.Outlined.Add, contentDescription = "Add")
                            }
                        }
                        program.days?.let { days ->

                            days.forEach { day ->
                                if (day.title != null)
                                    DayItem(
                                        day = day,
                                        onDayUpsertScreenClick = { onDayUpsertScreenClick(day) },
                                        onDeleteRequest = { onDayDeleteRequest(day) },
                                        navigateToDayScreen = navigateToDayScreen

                                    )
                            }
                        }
                    }


            }
        )
    }
}

@Composable
fun DayItem(
    day: Day,
    onDayUpsertScreenClick: (Day?) -> Unit,
    onDeleteRequest: () -> Unit,
    navigateToDayScreen: (Day) -> Unit
) {
    var dayOptionsExpanded by remember { mutableStateOf(false) }
    OutlinedCard(
        modifier = Modifier.clickable {
            navigateToDayScreen(day)
        }

    ) {
        ListItem(
            headlineContent = {
                day.title?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            },
            trailingContent = {

                Box(
                ) {
                    Icon(
                        Icons.Default.MoreVert, contentDescription = "More options",
                        modifier = Modifier.clip(
                            CircleShape
                        ).clickable { dayOptionsExpanded = !dayOptionsExpanded }
                            .padding(vertical = 4.dp)
                    )

                    DropdownMenu(
                        expanded = dayOptionsExpanded,
                        onDismissRequest = { dayOptionsExpanded = false }
                    ) {
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "Edit"
                                )
                            },
                            text = { Text("Edit") },
                            onClick = {
                                dayOptionsExpanded = false
                                onDayUpsertScreenClick(day)
                            }

                        )
                        HorizontalDivider()
                        DropdownMenuItem(
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "Delete"
                                )
                            },
                            text = { Text("Delete") },
                            onClick = {
                                dayOptionsExpanded = false // Keep this local dismissal
                                onDeleteRequest()        // Call the new lambda
                            }

                        )
                    }
                }


            }
        )

    }

}
