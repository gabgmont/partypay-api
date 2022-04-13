package br.com.fairie.partypay.endpoints.menu.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class ResumedOrderDTO(
        @JsonProperty(value = "order_id")
        val id: Long,
        @JsonProperty(value = "name")
        val name: String,
        @JsonProperty(value = "image")
        val image: String,
        @JsonProperty(value = "value")
        val value: BigDecimal
)
