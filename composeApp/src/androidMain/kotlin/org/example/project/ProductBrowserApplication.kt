package org.example.project

import android.app.Application
import org.example.project.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ProductBrowserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProductBrowserApplication)
            modules(appModule)
        }
    }
}
