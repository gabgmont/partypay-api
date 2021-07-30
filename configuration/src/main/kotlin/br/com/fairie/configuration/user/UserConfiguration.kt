package br.com.fairie.configuration.user

import br.com.fairie.user.UserRepository
import br.com.fairie.user.UserUseCase
import br.com.fairie.endpoints.user.controller.UserController
import br.com.fairie.user.repository.UserRepositoryImpl
import br.com.fairie.user.usecase.UserUseCaseImpl
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