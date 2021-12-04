package br.com.fairie.partypay.endpoints.user.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ResumedUserDTO(
        @JsonProperty(value = "name")
        val name: String,
        @JsonProperty(value = "cpf")
        val cpf: String
) {
    override fun toString(): String {
        return "[name='$name', cpf='$cpf']"
    }
}