package com.brucej.wanandroid_kotlin.base

import kotlinx.coroutines.Job
import java.util.ArrayList

open class BaseModel {
    //管理 Coroutinue 执行任务
    private val jobList: MutableList<Job>? by lazy {
        mutableListOf<Job>()
    }

    fun addJob(job: Job) = jobList?.add(job)
    fun cancleJob() = jobList?.let {
        jobList?.forEach { job: Job ->
            let {
                if (!job.isCancelled) {
                    job.cancel()
                }
            }
        }
    }
}
