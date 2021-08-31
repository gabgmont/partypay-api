package br.com.fairie.partypay.configuration.user

import br.com.fairie.partypay.configuration.repository.RepositoryConfiguration.Companion.H2_JDBC_TEMPLATE
import br.com.fairie.partypay.endpoints.user.controller.UserController
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.usecase.user.impl.UserUseCaseImpl
import br.com.fairie.partypay.user.repository.UserRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.beans.factory.annotation.Qualifier

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
    fun loadUserRepository(
        @Qualifier(H2_JDBC_TEMPLATE) jdbc: JdbcTemplate
    ): UserRepository =
        UserRepositoryImpl(jdbc)
}