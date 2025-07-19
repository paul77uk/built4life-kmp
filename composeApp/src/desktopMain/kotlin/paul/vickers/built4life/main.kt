package paul.vickers.built4life

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import paul.vickers.built4life.di.appModule
import javax.swing.SwingUtilities

fun main() {
    startKoin {
        modules(appModule)
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Built4Life",
        ) {
            App()
        }
    }
//    application {
//        val windowState = rememberWindowState()
//        val lifecycle = LifecycleRegistry()
//
//        val rootComponent = runOnUiThread {
//            RootComponent(DefaultComponentContext(lifecycle))
//        }
//
//        Window(
//            onCloseRequest = ::exitApplication,
//            title = "Built4Life"
//        ) {
//            LifecycleController(
//                lifecycle,
//                windowState = windowState,
//                windowInfo = LocalWindowInfo.current
//            )
//            App(rootComponent)
//        }
//    }
}

internal fun <T> runOnUiThread(block: () -> T): T {
    if (SwingUtilities.isEventDispatchThread()) {
        return block()
    }

    var error: Throwable? = null
    var result: T? = null

    SwingUtilities.invokeAndWait {
        try {
            result = block()
        } catch (e: Throwable) {
            error = e
        }
    }

    error?.also { throw it }

    return result as T
}


