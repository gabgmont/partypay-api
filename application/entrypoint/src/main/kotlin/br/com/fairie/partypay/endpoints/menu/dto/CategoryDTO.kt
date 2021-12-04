package br.com.fairie.partypay.endpoints.menu.dto

import com.fasterxml.jackson.annotation.JsonProperty

class CategoryDTO(
        @JsonProperty(value = "name")
        val name: String,
        @JsonProperty(value = "order_list")
        val orders: List<OrderDTO>
)