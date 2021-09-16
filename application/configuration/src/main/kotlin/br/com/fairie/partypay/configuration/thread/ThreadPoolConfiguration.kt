package br.com.fairie.partypay.configuration.thread

import br.com.fairie.partypay.utils.ThreadPool
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

@Configuration
open class ThreadPoolConfiguration {

    @Bean
    open fun loadThreadPoolExecutor(): ThreadPoolExecutor =
        Executors.newCachedThreadPool() as ThreadPoolExecutor

    @Bean
    open fun loadThreadPool(
        threadPoolExecutor: ThreadPoolExecutor
    ) = ThreadPool(threadPoolExecutor)
}