package com.lahza.todo

import android.app.Application
import com.lahza.todo.utils.AppContextProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppContextProvider.setContext(this)
    }
}