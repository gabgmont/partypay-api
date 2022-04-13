package br.com.fairie.partypay.repositories.menu.json.dao

import com.fasterxml.jackson.annotation.JsonProperty

class MenuDao(
    @get:JsonProperty("id")
    val id: Long,
    @get:JsonProperty("restaurant")
    val restaurant: String,
    @get:JsonProperty("image")
    val image: String,
    @get:JsonProperty("menu")
    val menu: List<CategoryDao>
)
