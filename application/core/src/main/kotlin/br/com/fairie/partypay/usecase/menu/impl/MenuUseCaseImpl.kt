package br.com.fairie.partypay.usecase.menu.impl

import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order

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
        return repository.getOrderByName(restaurant, order)
    }
}