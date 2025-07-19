package paul.vickers.built4life

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import paul.vickers.built4life.theme.AppTheme
import paul.vickers.built4life.ui.navigation.NavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview
//fun App(component: RootComponent) {
//    RootScreen(component)
fun App() {
    AppTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.primary,
        ) { innerPadding ->
            NavGraph(
                navController = navController,
                innerPadding = innerPadding
            )
        }
    }
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Top App bar") },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
//                ),
//                navigationIcon = {
//                    IconButton(
//                        onClick = { /* doSomething() */ }
//                    ) {
//                        Icon(
//                            Icons.AutoMirrored.Default.ArrowBack,
//                            contentDescription = "nav back arrow"
//                        )
//                    }
//                }
//            )
//        }
//    ) { padding ->
//        Text("Hello World", modifier = Modifier.padding(padding))
//    }
}