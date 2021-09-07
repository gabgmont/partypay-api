package br.com.fairie.partypay.endpoints.authentication.dto

class TokenDto(
    val type: String,
    val token: String,
    val expiration: String
)