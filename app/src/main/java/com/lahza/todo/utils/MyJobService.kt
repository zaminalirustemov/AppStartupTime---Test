package com.lahza.todo.utils

import android.annotation.SuppressLint
import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

@SuppressLint("SpecifyJobSchedulerIdRange")
class MyJobService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("MyJobService", "onStartJob: Job started")
        println("job.....")
        jobFinished(params, false)
        Log.d("MyJobService", "onStartJob: Job finished")
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("MyJobService", "onStopJob: Job stopped")
        return true
    }

}