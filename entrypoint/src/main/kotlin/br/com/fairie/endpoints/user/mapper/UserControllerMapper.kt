package br.com.fairie.endpoints.user.mapper

import br.com.fairie.shared.dto.CPFDto
import br.com.fairie.shared.vo.CPF
import br.com.fairie.endpoints.user.dto.UserDto
import br.com.fairie.user.entity.User

fun CPFDto.toCPF(): CPF = CPF(cpf!!)

fun User.toDto(): UserDto =
    UserDto(
        nome,
        cpf.value,
        email.value,
        phone.value,
        photo?.value ?: "No Photo"
    )
