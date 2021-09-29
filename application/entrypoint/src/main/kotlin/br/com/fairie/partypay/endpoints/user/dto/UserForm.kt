package br.com.fairie.partypay.endpoints.user.dto

class UserForm(
    val name: String,
    val cpf: String,
    val password: String,
    val email: String,
    val phone: String,
    val photo: String?
)