package com.example.mvvmtestproject.di

import android.content.Context
import android.content.SharedPreferences
import com.example.mvvmtestproject.app.MyApp
import com.example.mvvmtestproject.utils.constants.Constant.SHARED_PREF_FILE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object PrefModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences =
        MyApp.getAppContext().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
}