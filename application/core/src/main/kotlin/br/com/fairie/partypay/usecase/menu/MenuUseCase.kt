package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order

interface MenuUseCase {
    fun getMenu(menu: String): Menu
    fun getMenuCategory(restaurant: String, category: String): Category
    fun getMenuOrder(restaurant: String, order: String): Order
}