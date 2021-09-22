package br.com.fairie.partypay.repositories.user.mapper

import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.repositories.user.dao.UserDao
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo

fun List<UserDao>.toUserList(): List<User> {
    val userList = ArrayList<User>()

    forEach { entity ->
        userList.add(
            User(
                id = entity.id,
                name = entity.name,
                cpf = CPF(entity.cpf),
                email = Email(entity.email),
                secret = entity.secret,
                phone = Phone(entity.phone),
                photo = Photo(entity.photo?: ""),
                profiles = arrayListOf()
            )
        )
    }

    return userList
}