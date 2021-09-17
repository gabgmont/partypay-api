package br.com.fairie.partypay.handler.dto

class ErrorDto(
    val code: Int,
    val message: String
) {
    override fun toString(): String {
        return "[code=$code, message='$message']"
    }
}