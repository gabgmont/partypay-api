package br.com.fairie.user.dao

import br.com.fairie.shared.vo.CPF
import br.com.fairie.shared.vo.Email
import br.com.fairie.shared.vo.Phone
import br.com.fairie.shared.vo.Photo
import br.com.fairie.user.entity.User

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