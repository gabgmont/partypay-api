package br.com.fairie.partypay.endpoints.menu.dto

class CategoryDTO(
    val name: String,
    val orderList: List<OrderDTO>
) {
}