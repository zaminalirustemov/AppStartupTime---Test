package com.lahza.todo.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AppContextProvider {
    private lateinit var context: Context

    fun setContext(appContext: Context) {
        context = appContext
    }

    fun getAppContext(): Context {
        return context
    }
}
