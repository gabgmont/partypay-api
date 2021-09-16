package br.com.fairie.partypay.utils

import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPool(
    val executor: ThreadPoolExecutor
) {

    init {
        configureThreadPool()
    }

    private fun configureThreadPool() {
        this.executor.corePoolSize = 5
        this.executor.maximumPoolSize = 100
        this.executor.allowCoreThreadTimeOut(true)
        this.executor.setKeepAliveTime(30, TimeUnit.SECONDS)
    }
}