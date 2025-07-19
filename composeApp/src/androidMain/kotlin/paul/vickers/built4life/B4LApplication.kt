package paul.vickers.built4life

import android.app.Application
import org.koin.core.context.startKoin
import paul.vickers.built4life.di.appModule

class B4LApplication: Application() {

    companion object {
        lateinit var b4lApplication: Application
    }

    override fun onCreate() {
        super.onCreate()

        b4lApplication = this

        startKoin {
            modules(appModule)
        }
    }
}