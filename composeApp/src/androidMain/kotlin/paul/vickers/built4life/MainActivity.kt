package paul.vickers.built4life

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            // this is because we are only using one theme to look same or light and dark
            // and because we have a black background on our appbar, but we are in light mode
            // we use the dark style as this makes our notification icons white as they are in dark mode
            // as being on a dark background, otherwise our notification icons would be black on older phones
            statusBarStyle = SystemBarStyle.dark(
                Color.BLACK,
            ),
            navigationBarStyle = SystemBarStyle.dark(
                Color.BLACK,
                )

//            // this is because we are only using one theme to look same or light and dark, so just implement light
//            statusBarStyle = SystemBarStyle.light(
//                Color.BLACK, // Use black for status bar,
//                Color.BLACK // Use black for status bar,
//            ),
//            navigationBarStyle = SystemBarStyle.light(
//                Color.BLACK, // Use black for navigation bar,
//                Color.BLACK // Use black for navigation bar,
//            )
        )
        super.onCreate(savedInstanceState)

//        val root = retainedComponent { context ->
//            RootComponent(context)
//        }

        setContent {
//            App(root)
            App()
        }
    }
}