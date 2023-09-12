package com.app.natifetest

import android.app.Application
import com.app.core.network.networkModule
import com.app.domain.use_case.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, useCaseModule)
        }
    }
}