package br.com.fairie.partypay.endpoints.user.dto

import com.fasterxml.jackson.annotation.JsonProperty

class UserDTO(
        @JsonProperty(value = "name")
        val name: String,
        @JsonProperty(value = "cpf")
        val cpf: String,
        @JsonProperty(value = "email")
        val email: String,
        @JsonProperty(value = "phone")
        val phone: String,
        @JsonProperty(value = "photo")
        val photo: String
) {
    override fun toString(): String {
        return "[name='$name', cpf='$cpf', email='$email', phone='$phone', photo='$photo']"
    }
}