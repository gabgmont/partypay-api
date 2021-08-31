package br.com.fairie.partypay.user.repository.mapper

import br.com.fairie.partypay.usecase.user.entity.User
import br.com.fairie.partypay.user.dao.UserEntity
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo

fun List<UserEntity>.toUserList(): List<User> {
    val userList = ArrayList<User>()

    forEach { entity ->
        userList.add(
            User(
                nome = entity.name,
                cpf = CPF(entity.cpf),
                email = Email(entity.email),
                phone = Phone(entity.phone),
                photo = Photo(entity.photo?: "")
            )
        )
    }

    return userList
}