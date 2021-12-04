package br.com.fairie.partypay.endpoints.user.dto

class ResumedUserDTO(
    val name: String,
    val cpf: String
) {
    override fun toString(): String {
        return "[name='$name', cpf='$cpf']"
    }
}