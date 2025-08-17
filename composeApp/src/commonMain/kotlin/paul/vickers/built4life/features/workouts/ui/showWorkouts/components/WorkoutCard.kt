package paul.vickers.built4life.features.workouts.ui.showWorkouts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import paul.vickers.built4life.features.workouts.model.WorkoutWithMaxScore

@Composable
fun WorkoutCard(
    workout: WorkoutWithMaxScore,
    onAddEditClick: () -> Unit,
    onLogScoreClick: () -> Unit
) {
    OutlinedCard() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                workout.title,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
            )
            IconButton(
                onClick = onAddEditClick
            ) {
                Icon(
                    Icons.Outlined.MoreVert,
                    contentDescription = "Edit Workout",
                )
            }

        }

        if (workout.reps == null)
            OutlinedButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                onClick = onLogScoreClick
            ) {
                Text(
                    "LOG SCORE",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        else {
            ScoreComponent(
                workout = workout,
                onEditScoreClick = onLogScoreClick,
            )
        }
    }
}