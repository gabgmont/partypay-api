package br.com.fairie.partypay.configuration.endpoints.handler

import br.com.fairie.partypay.handler.ExceptionHandler
import br.com.fairie.partypay.handler.GenericExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ExceptionHandlerConfiguration {

    @Bean
    open fun loadExceptionHandler() = ExceptionHandler()

    @Bean
    open fun loadGenericExceptionHandler() = GenericExceptionHandler()
}