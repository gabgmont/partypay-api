package br.com.fairie.partypay.menu.dao

import com.fasterxml.jackson.annotation.JsonProperty

class CategoryDao(
    @get:JsonProperty("name")
    val name: String,
    @get:JsonProperty("orders")
    val orders: List<OrderDao>
)