package com.lahza.todo.utils

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Context.JOB_SCHEDULER_SERVICE

class JobSchedulerUseCase(private val context: Context) {

    fun scheduleJob() {
        val jobServiceComponent = ComponentName(context, MyJobService::class.java)
        val builder = JobInfo.Builder(JOB_ID, jobServiceComponent)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .setPersisted(true)

        val jobScheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())
    }

    companion object {
        private const val JOB_ID = 123 
    }
}
