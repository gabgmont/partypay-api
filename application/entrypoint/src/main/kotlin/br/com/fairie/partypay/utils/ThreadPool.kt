package br.com.fairie.partypay.utils

import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPool(
    val executor: ThreadPoolExecutor
) {

    init {
        executor.corePoolSize = 5
        executor.maximumPoolSize = 100
        executor.allowCoreThreadTimeOut(true)
        executor.setKeepAliveTime(30, TimeUnit.SECONDS)
    }
}