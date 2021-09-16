package br.com.fairie.partypay.usecase.menu.impl

import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order

class MenuUseCaseImpl(
    private val repository: MenuRepository
) : MenuUseCase {

    override fun getMenu(menu: String): Menu {
        return repository.getMenuByName(menu)
    }

    override fun getMenuCategory(restaurant: String, category: String): Category {
        return repository.getCategoryByName(restaurant, category)
    }

    override fun getMenuOrder(restaurant: String, order: String): Order {
        Thread.sleep(10000)

        return repository.getOrderByName(restaurant, order)
    }
}