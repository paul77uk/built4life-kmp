package paul.vickers.built4life.ui.showWorkouts.components

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
import paul.vickers.built4life.model.Score
import kotlin.math.roundToLong

@Composable
fun ScoreComponent(
    scores: List<Score>,
    onEditScoreClick: () -> Unit,
    eliteLevel: Long? = null,
    weight: Long? = null
) {
    val maxScore = scores.maxByOrNull { it.reps }?.reps ?: 0
    val repMax = weight?.times((1 + 0.0333 * maxScore))?.roundToLong()
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
            Text("$maxScore", color = Color(0xff397DD1), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(start = 4.dp))
            Text(
                "REPS",
                color = Color(0xff397DD1),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 4.dp)
            )
            if (weight != null) {
                Spacer(modifier = Modifier.padding(start = 4.dp))
                Text(
                    "x ",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color(0xff397DD1),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    "$weight",
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
            if (weight != null)
                OneRepMaxText(
                    text = "$repMax"
                )
            eliteLevel?.let {
                ProgressBar(
                    progress = if (weight != null) repMax!!.toFloat() / 100 else maxScore.toFloat() / 100
                )
                LevelText(
                    maxScore = if (weight != null) repMax!! else maxScore,
                    eliteLevel = it
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
    maxScore: Long,
    eliteLevel: Long
) {
    val maxScore = maxScore.toFloat() / 100
    val level = (eliteLevel.toFloat() / 100) / 5
    val beginner = level * 1
    val novice = level * 2
    val intermediate = level * 3
    val advanced = level * 4
//    val elite = level * 5

    val text = when (maxScore) {
        in 0.0f..beginner -> "Beginner"
        in beginner..novice -> "Novice"
        in novice..intermediate -> "Intermediate"
        in intermediate..advanced -> "Advanced"
        else -> "Elite"
    }

    Text(text, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
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
