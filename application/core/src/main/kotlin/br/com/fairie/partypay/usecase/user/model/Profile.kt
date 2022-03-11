package br.com.fairie.partypay.usecase.user.model

data class Profile(
    val id: Long,
    val type: Type
){
    enum class Type{
        STANDARD, SOCIAL
    }
}
