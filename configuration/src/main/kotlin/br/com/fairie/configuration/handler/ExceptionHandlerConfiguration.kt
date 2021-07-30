package br.com.fairie.configuration.handler

import br.com.fairie.endpoints.handler.BadRequestExceptionHandler
import br.com.fairie.endpoints.handler.UserNotFoundExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ExceptionHandlerConfiguration {

    @Bean
    fun loadUserNotFoundExceptionHandler() = UserNotFoundExceptionHandler()

    @Bean
    fun loadBadRequestExceptionHandler() = BadRequestExceptionHandler()


}