package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order

interface MenuUseCase {
    fun getMenu(menu: String): Menu
    fun getMenuCategory(restaurant: String, category: String): Category
    fun getMenuOrder(restaurant: String, order: String): Order
}