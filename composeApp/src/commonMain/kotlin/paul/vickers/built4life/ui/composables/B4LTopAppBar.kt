package paul.vickers.built4life.ui.composables

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import paul.vickers.built4life.utils.ScreenAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun B4LTopAppBar(
    onClick: () -> Unit,
    onBackIconClick: () -> Unit = {},
    screenAction: ScreenAction,
//    title: String,
//    navigationIcon: @Composable () -> Unit = {},
//    actions: @Composable () -> Unit = {},
) {
//    when (windowSizeClass) {
//        // mobile
//        WindowWidthSizeClass.COMPACT ->
//            Text("mobile")
//        // tablet
//        WindowWidthSizeClass.MEDIUM ->
//            Text("tablet")
//        // desktop
//        WindowWidthSizeClass.EXPANDED ->
//            Text("desktop")
//    }
//    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
//    val viewModel = koinViewModel<UpsertWorkoutViewModel>()
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
    TopAppBar(
        windowInsets = WindowInsets(
            left = 4.dp
        ),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            when (screenAction) {
                ScreenAction.EDIT_WORKOUT, ScreenAction.ADD_WORKOUT, ScreenAction.ADD_SCORE ->
                    IconButton(onClick = onBackIconClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                else -> {}
            }
        },
        title = {
            Text(
                text =
                    when (screenAction) {
                        ScreenAction.HOME -> "built4life"
                        ScreenAction.EDIT_WORKOUT -> "Edit Workout"
                        ScreenAction.ADD_WORKOUT -> "Add Workout"
                        ScreenAction.ADD_SCORE -> "Add Score"
                    }.uppercase(),
                fontWeight = FontWeight.Bold,
            )
//                    if (windowSizeClass == WindowWidthSizeClass.MEDIUM || windowSizeClass == WindowWidthSizeClass.EXPANDED)
//                        B4LSearchBar(modifier = Modifier.weight(1f))
        },
        actions = {
            when (screenAction) {
                ScreenAction.HOME ->
                    IconButton(
                        onClick = onClick,
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add",
                        )
                    }

                ScreenAction.EDIT_WORKOUT ->
                    IconButton(
                        onClick = onClick
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete"
                        )
                    }
                else -> {}
            }
        }
    )
}

//        if (windowSizeClass == WindowWidthSizeClass.COMPACT)
//            B4LSearchBar(
//                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary)
//                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
//            )


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun B4LSearchBar(modifier: Modifier = Modifier) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        ),
        leadingIcon = {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = "Chevron",
                    modifier = Modifier.rotate(90f),
                )
            }
        },
        modifier =
            if (windowSizeClass == WindowWidthSizeClass.EXPANDED)
                Modifier.fillMaxWidth(0.6f)
            else
                modifier.fillMaxWidth(),
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                "Search",
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                )
            }
        },
    )
}
