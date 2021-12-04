package br.com.fairie.partypay.endpoints.menu.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class ResumedOrderDTO(
        @JsonProperty(value = "name")
        val name: String,
        @JsonProperty(value = "value")
        val value: BigDecimal
)