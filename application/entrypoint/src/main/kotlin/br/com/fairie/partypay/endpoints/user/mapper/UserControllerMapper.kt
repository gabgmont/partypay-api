package br.com.fairie.partypay.endpoints.user.mapper

import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.endpoints.user.dto.UserForm
import br.com.fairie.partypay.shared.dto.CPFForm
import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun CPFForm.toCPForNull(): CPF? {
    if (cpf.isNullOrEmpty()) return null
    return CPF(cpf)
}

fun List<User>.toDto(): List<UserDTO> = map { user -> user.toDTO() }

fun User.toDTO(): UserDTO = UserDTO(
        name = name,
        cpf = cpf.value,
        email = email.value,
        phone = phone.value,
        photo = photo?.value ?: "",
)

fun UserForm.toVo(): User = User(
        id = 0,
        name = name,
        cpf = CPF(cpf),
        email = Email(email),
        secret = BCryptPasswordEncoder().encode(password),
        phone = Phone(phone),
        photo = Photo(photo ?: ""),
        profiles = arrayListOf(),
)
