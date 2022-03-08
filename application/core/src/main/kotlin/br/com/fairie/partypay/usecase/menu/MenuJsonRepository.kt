package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.model.Category
import br.com.fairie.partypay.usecase.menu.model.Menu
import br.com.fairie.partypay.usecase.menu.model.Order

interface MenuJsonRepository {
    fun getMenuByName(restaurant: String): Menu
    fun getCategoryByName(restaurant: String, category: String): Category
    fun getOrderByName(restaurant: String, order: String): Order
}
