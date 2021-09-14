package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order

interface MenuRepository {
    fun getMenuByName(restaurant: String): Menu
    fun getCategoryByName(restaurant: String, category: String): Category
    fun getOrderByName(restaurant: String, order: String): Order
}