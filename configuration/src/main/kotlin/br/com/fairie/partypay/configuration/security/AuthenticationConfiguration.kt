package br.com.fairie.partypay.configuration.security

import br.com.fairie.partypay.configuration.security.filter.AuthenticationTokenFilter
import br.com.fairie.partypay.endpoints.authentication.controller.AuthenticationController
import br.com.fairie.partypay.usecase.authentication.AuthService
import br.com.fairie.partypay.usecase.authentication.AuthenticationUseCase
import br.com.fairie.partypay.usecase.authentication.impl.AuthenticationUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthenticationConfiguration {

    @Bean
    fun loadAuthController(
        useCase: AuthenticationUseCase
    ) = AuthenticationController(useCase)

    @Bean
    fun loadAuthUseCase(
        service: AuthService
    ): AuthenticationUseCase =
        AuthenticationUseCaseImpl(service)
}