package paul.vickers.built4life.features.workouts.ui.showWorkouts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import paul.vickers.built4life.features.workouts.model.WorkoutWithMaxScore

@Composable
fun ScoreComponent(
    workout: WorkoutWithMaxScore,
    onEditScoreClick: () -> Unit,
) {
    Spacer(
        modifier = Modifier.padding(top = 8.dp)
    )
    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("${workout.reps}", color = Color(0xff397DD1), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(start = 4.dp))
            Text(
                "REPS",
                color = Color(0xff397DD1),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 4.dp)
            )
            if (workout.weight != null) {
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Text(
                    "x ",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color(0xff397DD1),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    workout.weight.toString(),
                    color = Color(0xff397DD1),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    " KG",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color(0xff397DD1),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                )

            }

            Spacer(modifier = Modifier.padding(start = 16.dp))
        }

        Column(
            modifier = Modifier.weight(1f).padding(bottom = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (workout.weight != null)
                OneRepMaxText(
                    text = "${workout.oneRepMax}"
                )
            workout.level?.let {
                if (workout.level.isNotEmpty())
                    ProgressBar(
                        progress = workout.progress?.toFloat() ?: 0f
                    )
                if (workout.weight != null) workout.oneRepMax
                if (workout.level.isNotEmpty())
                    LevelText(
                        level = workout.level
                    )
            }
        }

        IconButton(
            onClick = { onEditScoreClick() }
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Edit Score",
            )
        }
    }
}

@Composable
private fun ProgressBar(
    modifier: Modifier = Modifier,
    progress: Float
) {
    LinearProgressIndicator(
        gapSize = (-4).dp,
        modifier = modifier.height(6.dp),
        color = Color(0xff397DD1),
        trackColor = Color.LightGray,
        progress = { progress },
        drawStopIndicator = {
            false
        }
    )
}

@Composable
fun LevelText(
    level: String?
) {


    level?.let { Text(it, fontSize = 12.sp, fontWeight = FontWeight.SemiBold) }
}

@Composable
fun OneRepMaxText(
    text: String
) {
    Text(
        "1 REP MAX: $text KG",
        textAlign = TextAlign.Center,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
    )

}
