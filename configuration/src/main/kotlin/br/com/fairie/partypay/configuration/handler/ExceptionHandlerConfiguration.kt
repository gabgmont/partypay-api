package br.com.fairie.partypay.configuration.handler

import br.com.fairie.partypay.endpoints.handler.HttpExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExceptionHandlerConfiguration {

    @Bean
    fun loadUserNotFoundExceptionHandler() = HttpExceptionHandler()

}