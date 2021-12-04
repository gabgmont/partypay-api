package br.com.fairie.partypay.endpoints.user.dto

import com.fasterxml.jackson.annotation.JsonProperty

class UserForm(
        @JsonProperty(value = "name")
        val name: String,
        @JsonProperty(value = "cpf")
        val cpf: String,
        @JsonProperty(value = "password")
        val password: String,
        @JsonProperty(value = "email")
        val email: String,
        @JsonProperty(value = "phone")
        val phone: String,
        @JsonProperty(value = "photo")
        val photo: String?
)