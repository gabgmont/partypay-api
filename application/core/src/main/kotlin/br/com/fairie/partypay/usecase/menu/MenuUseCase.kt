package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order

interface MenuUseCase {
    fun getRestaurants(): List<Menu>
    fun getMenu(id: Long): Menu
    fun getMenuCategory(categoryId: Long): Category
    fun getMenuOrder(orderId: Long): Order
}
