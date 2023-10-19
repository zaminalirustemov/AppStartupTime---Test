package com.lahza.todo.utils

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE
import android.os.Build
import androidx.annotation.RequiresApi

class JobSchedulerUseCase(private val context: Context) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun scheduleJob() {
        val jobServiceComponent = ComponentName(context, MyJobService::class.java)
        val builder = JobInfo.Builder(JOB_ID, jobServiceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .setPersisted(true)

        val jobScheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())
    }

    companion object {
        private const val JOB_ID = 123
    }
}
