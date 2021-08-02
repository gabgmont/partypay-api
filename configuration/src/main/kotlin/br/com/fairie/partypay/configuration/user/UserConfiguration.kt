package br.com.fairie.partypay.configuration.user

import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.endpoints.user.controller.UserController
import br.com.fairie.partypay.user.repository.UserRepositoryImpl
import br.com.fairie.partypay.usecase.user.impl.UserUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfiguration {

    @Bean
    fun loadUserController(
        useCase: UserUseCase
    ) = UserController(useCase)

    @Bean
    fun loadUserUseCase(
        repository: UserRepository
    ): UserUseCase =
        UserUseCaseImpl(repository)

    @Bean
    fun loadUserRepository(): UserRepository =
        UserRepositoryImpl()
}