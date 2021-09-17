package br.com.fairie.partypay.configuration.endpoints.handler

import br.com.fairie.partypay.handler.HttpExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ExceptionHandlerConfiguration {

    @Bean
    open fun loadUserNotFoundExceptionHandler() = HttpExceptionHandler()

}