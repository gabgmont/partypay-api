package br.com.fairie.partypay.configuration.endpoints.session

import br.com.fairie.partypay.endpoints.session.controller.SessionController
import br.com.fairie.partypay.repositories.session.repository.SessionRepositoryImpl
import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.SessionUseCase
import br.com.fairie.partypay.usecase.session.impl.SessionUseCaseImpl
import br.com.fairie.partypay.usecase.user.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SessionConfiguration {

    @Bean
    open fun loadSessionController(
        useCase: SessionUseCase
    ) = SessionController(useCase)

    @Bean
    open fun loadSessionUseCase(
        sessionRepository: SessionRepository,
        userRepository: UserRepository,
        menuRepository: MenuRepository
    ): SessionUseCase = SessionUseCaseImpl(sessionRepository, userRepository, menuRepository)

    @Bean
    open fun loadSessionRepository(
    ): SessionRepository = SessionRepositoryImpl()
}
