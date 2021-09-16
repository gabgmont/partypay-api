package br.com.fairie.partypay.configuration.endpoints.menu

import br.com.fairie.partypay.endpoints.menu.controller.MenuController
import br.com.fairie.partypay.menu.repository.MenuRepositoryImpl
import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import br.com.fairie.partypay.usecase.menu.impl.MenuUseCaseImpl
import br.com.fairie.partypay.utils.ThreadPool
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MenuConfiguration {

    @Bean
    open fun loadMenuController(
        threadPool: ThreadPool,
        useCase: MenuUseCase
    ) = MenuController(threadPool, useCase)

    @Bean
    open fun loadMenuUseCase(
        repository: MenuRepository
    ): MenuUseCase =
        MenuUseCaseImpl(repository)

    @Bean
    open fun loadMenuRepository(): MenuRepository =
        MenuRepositoryImpl()
}