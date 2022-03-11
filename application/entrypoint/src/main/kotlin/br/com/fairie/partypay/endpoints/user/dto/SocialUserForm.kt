package br.com.fairie.partypay.endpoints.user.dto

import com.fasterxml.jackson.annotation.JsonProperty

class SocialUserForm(
    @JsonProperty(value = "name")
    val name: String,
    @JsonProperty(value = "email")
    val email: String,
    @JsonProperty(value = "photo")
    val photo: String?
)
