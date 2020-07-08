package com.samplepaggingapp.utils

import android.app.Application
import com.samplepaggingapp.di.AppComponent
import com.samplepaggingapp.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}