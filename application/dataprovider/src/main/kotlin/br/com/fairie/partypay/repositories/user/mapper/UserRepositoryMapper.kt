package br.com.fairie.partypay.repositories.user.mapper

import br.com.fairie.partypay.repositories.user.dao.UserDao
import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo

fun List<UserDao>.toUserList(): List<User> = map { userDao -> userDao.toModel() }

fun UserDao.toModel() = User(
        id = id,
        name = name,
        cpf = CPF(cpf),
        email = Email(email),
        secret = secret,
        phone = Phone(phone),
        photo = Photo(photo ?: ""),
        profiles = arrayListOf()
)