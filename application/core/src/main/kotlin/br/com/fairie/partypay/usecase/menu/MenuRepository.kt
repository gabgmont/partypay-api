package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.vo.Order

interface MenuRepository {
    fun getOrderByName(orderName: String): Order
    fun saveOrder(order: Order)
}