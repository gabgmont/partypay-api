package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.model.Category
import br.com.fairie.partypay.usecase.menu.model.Menu
import br.com.fairie.partypay.usecase.menu.model.Order

interface MenuUseCase {
    fun getRestaurants(): List<Menu>
    fun getMenu(id: Long): Menu
    fun getMenuCategory(categoryId: Long): Category
    fun getMenuOrder(orderId: Long): Order
}
