package br.com.fairie.partypay.user.dao

import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo
import br.com.fairie.partypay.usecase.user.entity.User

class UserDao(
    val name: String,
    val cpf: String,
    val email: String,
    val phone: String,
    val photo: String
) {
    companion object {
        const val COLUMN_NAME = "name"
        const val COLUMN_CPF = "cpf"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_PHOTO = "photo"

        fun UserDao.toUserVo(): User =
            User(
                name,
                CPF(cpf),
                Email(email),
                Phone(phone),
                Photo(photo)
            )
    }
}