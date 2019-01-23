package com.lostthings.app

import android.app.Application
import com.lostthings.app.di.appModules
import org.koin.android.ext.android.startKoin

class LostThingsApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}
