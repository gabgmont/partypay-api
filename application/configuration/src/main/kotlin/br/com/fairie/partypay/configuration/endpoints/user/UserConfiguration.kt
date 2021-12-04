package br.com.fairie.partypay.configuration.endpoints.user

import br.com.fairie.partypay.endpoints.user.controller.UserController
import br.com.fairie.partypay.repositories.user.repository.UserRepositoryImpl
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.usecase.user.impl.UserUseCaseImpl
import br.com.fairie.partypay.utils.ThreadPool
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class UserConfiguration {

    @Bean
    open fun loadUserController(
        threadPool: ThreadPool,
        useCase: UserUseCase
    ) = UserController(threadPool, useCase)

    @Bean
    open fun loadUserUseCase(
        repository: UserRepository
    ): UserUseCase =
        UserUseCaseImpl(repository)

    @Bean
    open fun loadUserRepository(): UserRepository =
        UserRepositoryImpl()
}