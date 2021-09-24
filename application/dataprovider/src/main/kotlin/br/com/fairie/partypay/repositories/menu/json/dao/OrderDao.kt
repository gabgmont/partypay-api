package br.com.fairie.partypay.repositories.menu.json.dao

import com.fasterxml.jackson.annotation.JsonProperty

class OrderDao(
    @get:JsonProperty("name")
    val name: String,
    @get:JsonProperty("description")
    val description: String,
    @get:JsonProperty("value")
    val value: Double
)