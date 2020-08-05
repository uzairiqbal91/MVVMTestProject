package com.example.mvvmtestproject.di


import android.content.Context
import com.example.mvvmtestproject.app.MyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppContextModule {

    @Singleton
    @Provides
    fun getApplicationContext(): Context = MyApp.getAppContext().applicationContext

}