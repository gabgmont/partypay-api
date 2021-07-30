package br.com.fairie.endpoints.user.dto

class UserDto(
    val name: String,
    val cpf: String,
    val email: String,
    val phone: String,
    val photo: String
) {
    override fun toString(): String {
        return "[name='$name', cpf='$cpf', email='$email', phone='$phone', photo='$photo']"
    }
}