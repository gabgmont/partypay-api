package br.com.fairie.partypay.endpoints.user.mapper

import br.com.fairie.partypay.shared.dto.CPFDto
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.endpoints.user.dto.UserDto
import br.com.fairie.partypay.usecase.user.entity.User

fun CPFDto.toCPForNull(): CPF? {
    if (cpf.isNullOrEmpty()) return null
    return CPF(cpf)
}

fun List<User>.toDto(): List<UserDto> {
    val userList = ArrayList<UserDto>()

    forEach { user ->
        userList.add(
            UserDto(
                user.nome,
                user.cpf.value,
                user.email.value,
                user.phone.value,
                user.photo?.value ?: "No Photo"
            )
        )
    }

    return userList
}
