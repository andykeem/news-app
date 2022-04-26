package loc.example.newsapiapp

import android.app.Application
import loc.example.newsapiapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initInject()
    }

    private fun initInject() {
        startKoin {
            modules(appModule)
            androidContext(this@App)
        }
    }
}