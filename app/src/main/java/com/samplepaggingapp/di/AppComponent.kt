package com.samplepaggingapp.di

import android.content.Context
import com.samplepaggingapp.view.PhotoActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(photoActivity: PhotoActivity)
}