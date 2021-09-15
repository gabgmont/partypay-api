package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order

interface MenuRepository {
    fun getMenuByName(restaurant: String): Menu
    fun getCategoryByName(restaurant: String, category: String): Category
    fun getOrderByName(restaurant: String, order: String): Order
}