package paul.vickers.built4life

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.core.context.startKoin
import paul.vickers.built4life.di.appModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule)
    }
//    val lifecycle = LifecycleRegistry()
//    val rootComponent = RootComponent(DefaultComponentContext(lifecycle))
    ComposeViewport(document.body!!) {
//        App(rootComponent)
        App()
    }
}