package com.iapps.ui.base

import android.app.Application
import com.iapps.domain.di.apiModules
import com.iapps.domain.di.databaseModule
import com.iapps.domain.di.netModules
import com.iapps.domain.di.repositoryModules
import com.iapps.domain.di.useCaseModules
import com.iapps.domain.di.viewModelModules
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
                useCaseModules,
                repositoryModules,
                viewModelModules
            )
        }
    }
}