package br.com.fairie.partypay.endpoints.menu.dto

class CategoryDto(
    val name: String,
    val orderList: List<OrderDto>
) {
}