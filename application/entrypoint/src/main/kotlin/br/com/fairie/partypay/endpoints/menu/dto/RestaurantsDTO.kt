package br.com.fairie.partypay.endpoints.menu.dto

import com.fasterxml.jackson.annotation.JsonProperty

class RestaurantsDTO(
        @JsonProperty(value = "id")
        val id: Long,
        @JsonProperty(value = "restaurant")
        val name: String,
        @JsonProperty(value = "image")
        val image: String
)
