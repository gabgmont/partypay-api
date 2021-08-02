package br.com.fairie.partypay.endpoints.handler.dto

class ErrorDto(
    val code: Int,
    val message: String
) {
    override fun toString(): String {
        return "[code=$code, message='$message']"
    }
}