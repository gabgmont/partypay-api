package br.com.fairie.partypay.endpoints.authentication.form

import com.fasterxml.jackson.annotation.JsonProperty

class LoginForm(
        @JsonProperty(value = "cpf")
        val cpf: String,
        @JsonProperty(value = "secret")
        val secret: String
)