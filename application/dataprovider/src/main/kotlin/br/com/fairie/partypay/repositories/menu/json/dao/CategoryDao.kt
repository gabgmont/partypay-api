package br.com.fairie.partypay.repositories.menu.json.dao

import com.fasterxml.jackson.annotation.JsonProperty

class CategoryDao(
    @get:JsonProperty("name")
    val name: String,
    @get:JsonProperty("orders")
    val orders: List<OrderDao>
)