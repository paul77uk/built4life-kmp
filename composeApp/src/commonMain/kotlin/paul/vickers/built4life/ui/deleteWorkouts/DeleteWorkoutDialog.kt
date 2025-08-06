package paul.vickers.built4life.ui.deleteWorkouts

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteWorkoutDialog(
    navigateBack: () -> Unit
) {
    val viewModel = koinViewModel<DeleteWorkoutViewModel>()
    val showDialog by viewModel.showDeleteDialog.collectAsStateWithLifecycle()
    val workoutId = viewModel.workoutId

    if (showDialog)
        BasicAlertDialog(
            onDismissRequest = viewModel::dismissDeleteDialog,
        ) {
            Surface(shape = RoundedCornerShape(10.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Delete workout", fontWeight = FontWeight.Bold)
                    Text("Are you sure you want to delete this workout?", fontSize = 14.sp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        OutlinedButton(
                            shape = RoundedCornerShape(10.dp),
                            onClick = viewModel::dismissDeleteDialog
                        ) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                viewModel.deleteWorkout()
                                navigateBack()
                            }
                        ) {
                            Text("Confirm")
                        }

                    }
                }
            }
        }
}

