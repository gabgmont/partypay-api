package br.com.fairie.partypay.endpoints.menu.dto

import com.fasterxml.jackson.annotation.JsonProperty

class MenuDTO(
    @JsonProperty(value = "name")
    val name: String,
    @JsonProperty(value = "category_list")
    val categories: List<CategoryDTO>
)
