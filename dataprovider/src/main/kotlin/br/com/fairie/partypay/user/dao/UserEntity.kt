package br.com.fairie.partypay.user.dao

class UserEntity(
    val id: Long,
    val name: String,
    val cpf: String,
    val email: String,
    val phone: String,
    val photo: String?
) {
    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_CPF = "cpf"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_PHOTO = "photo"

    }
}