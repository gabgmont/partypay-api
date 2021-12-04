package br.com.fairie.partypay.handler.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ErrorDTO(
        @JsonProperty(value = "code")
        val code: Int,
        @JsonProperty(value = "message")
        val message: String
) {
    override fun toString(): String {
        return "[code=$code, message='$message']"
    }
}