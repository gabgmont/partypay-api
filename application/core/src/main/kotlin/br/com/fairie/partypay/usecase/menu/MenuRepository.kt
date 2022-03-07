package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order

interface MenuRepository {
    fun saveMenu(menu: Menu)
    fun getRestaurants(): List<Menu>
    fun getMenuById(menuId: Long): Menu
    fun getCategoryById(categoryId: Long): Category
    fun getOrderById(orderId: Long): Order
    fun saveOrder(order: Order)
}
