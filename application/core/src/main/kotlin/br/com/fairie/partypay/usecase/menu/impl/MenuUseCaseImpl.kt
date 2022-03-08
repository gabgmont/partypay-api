package br.com.fairie.partypay.usecase.menu.impl

import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import br.com.fairie.partypay.usecase.menu.model.Category
import br.com.fairie.partypay.usecase.menu.model.Menu
import br.com.fairie.partypay.usecase.menu.model.Order

class MenuUseCaseImpl(
    private val menuRepo: MenuRepository
) : MenuUseCase {

    override fun getRestaurants(): List<Menu> {
        return menuRepo.getRestaurants()
    }

    override fun getMenu(id: Long): Menu {
        return menuRepo.getMenuById(id)
    }

    override fun getMenuCategory(categoryId: Long): Category {
        return menuRepo.getCategoryById(categoryId)
    }

    override fun getMenuOrder(orderId: Long): Order {
        return menuRepo.getOrderById(orderId)
    }
}
