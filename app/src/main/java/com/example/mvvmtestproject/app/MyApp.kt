package com.example.mvvmtestproject.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        ctx = this
    }

    companion object {
        val TAG: String = MyApp::class.java
            .simpleName
        lateinit var ctx: MyApp

        fun getAppContext(): MyApp {
            return ctx
        }
    }
}