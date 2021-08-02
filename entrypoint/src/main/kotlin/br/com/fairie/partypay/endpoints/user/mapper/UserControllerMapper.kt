package br.com.fairie.partypay.endpoints.user.mapper

import br.com.fairie.partypay.shared.dto.CPFDto
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.endpoints.user.dto.UserDto
import br.com.fairie.partypay.usecase.user.entity.User

fun CPFDto.toCPF(): CPF = CPF(cpf!!)

fun User.toDto(): UserDto =
    UserDto(
        nome,
        cpf.value,
        email.value,
        phone.value,
        photo?.value ?: "No Photo"
    )
