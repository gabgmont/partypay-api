package br.com.fairie.partypay.endpoints.user.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

class ResumedUserDTO(
        @JsonProperty(value = "name")
        val name: String,
        @JsonProperty(value = "username")
        val username: String
) {
    override fun toString(): String {
        return ObjectMapper().writeValueAsString(this)
    }
}
