package br.com.fairie.partypay.endpoints.authentication.dto

import com.fasterxml.jackson.annotation.JsonProperty

class TokenDto(
        @JsonProperty(value = "type")
        val type: String,
        @JsonProperty(value = "token")
        val token: String,
        @JsonProperty(value = "expiration")
        val expiration: String
)