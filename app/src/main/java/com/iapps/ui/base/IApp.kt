package com.iapps.ui.base

import android.app.Application
import com.iapps.di.apiModules
import com.iapps.di.databaseModule
import com.iapps.di.netModules
import com.iapps.di.repositoryModules
import com.iapps.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class IApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@IApp)
            modules(
                databaseModule,
                netModules,
                apiModules,
                repositoryModules,
                viewModelModules
            )
        }
    }
}