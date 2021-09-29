package br.com.fairie.partypay.endpoints.user.mapper

import br.com.fairie.partypay.shared.dto.CPFForm
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.usecase.user.vo.User

fun CPFForm.toCPForNull(): CPF? {
    if (cpf.isNullOrEmpty()) return null
    return CPF(cpf)
}

fun List<User>.toDto(): List<UserDTO> {
    val userList = ArrayList<UserDTO>()

    forEach { user ->
        userList.add(
            UserDTO(
                user.name,
                user.cpf.value,
                user.email.value,
                user.phone.value,
                user.photo?.value ?: "No Photo"
            )
        )
    }

    return userList
}

fun User.toDTO(): UserDTO {
    return UserDTO(
        name = name,
        cpf = cpf.value,
        email = email.value,
        phone = phone.value,
        photo = photo?.value ?: "",
    )
}
