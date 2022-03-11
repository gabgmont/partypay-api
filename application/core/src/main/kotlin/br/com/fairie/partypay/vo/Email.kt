package br.com.fairie.partypay.vo

class Email(
    val value: String
) {
    fun username() = value.substringBefore("@")
    fun domain() = value.substringAfter("@")
}
