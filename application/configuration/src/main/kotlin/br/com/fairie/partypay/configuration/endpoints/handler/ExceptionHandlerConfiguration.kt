package br.com.fairie.partypay.configuration.endpoints.handler

import br.com.fairie.partypay.handler.HttpExceptionHandler
import br.com.fairie.partypay.handler.UsecaseExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ExceptionHandlerConfiguration {

    @Bean
    open fun loadHttpExceptionHandler() = HttpExceptionHandler()

    @Bean
    open fun loadUseCaseExceptionHandler() = UsecaseExceptionHandler()

}