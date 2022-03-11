package br.com.fairie.partypay.endpoints.user.mapper

import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.endpoints.user.dto.UserForm
import br.com.fairie.partypay.usecase.user.model.User
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Photo
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun List<User>.toDto(): List<UserDTO> = map { user -> user.toDTO() }

fun User.toDTO(): UserDTO = UserDTO(
        name = name,
        username = username,
        email = email.value,
        photo = photo?.value ?: "",
)

fun UserForm.toVo(): User = User(
        id = 0,
        name = name,
        username = username,
        email = Email(email),
        secret = BCryptPasswordEncoder().encode(password),
        photo = Photo(photo ?: ""),
        profiles = arrayListOf(),
)
