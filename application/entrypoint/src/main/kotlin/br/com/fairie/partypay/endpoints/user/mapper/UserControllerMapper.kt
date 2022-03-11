package br.com.fairie.partypay.endpoints.user.mapper

import br.com.fairie.partypay.endpoints.user.dto.SocialUserForm
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.endpoints.user.dto.UserForm
import br.com.fairie.partypay.usecase.user.model.User
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Photo
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
const val SOCIAL_PASSWORD = "Qa#9#eMLNx3?JR3.2zr~v)gYF^88>XfBWw75Nemt9YjbQMNCWwW"

fun List<User>.toDto(): List<UserDTO> = map { user -> user.toDTO() }

fun User.toDTO(): UserDTO = UserDTO(
    name = name,
    username = username,
    email = email.value,
    photo = photo?.value ?: "",
)

fun UserForm.toModel(): User = User(
    id = 0,
    name = name,
    username = username,
    email = Email(email),
    secret = BCryptPasswordEncoder().encode(password),
    photo = Photo(photo ?: ""),
    profiles = arrayListOf(),
)

fun SocialUserForm.toModel(): User {
    val email = Email(email)

    return User(
        id = 0,
        name = name,
        username = email.username(),
        email = email,
        secret = BCryptPasswordEncoder().encode(SOCIAL_PASSWORD),
        photo = Photo(photo ?: ""),
        profiles = arrayListOf(),
    )
}
