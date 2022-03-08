package br.com.fairie.partypay.usecase.menu.model

data class Category(
    val id: Long,
    val name: String,
    val orderList: List<Order>
)
