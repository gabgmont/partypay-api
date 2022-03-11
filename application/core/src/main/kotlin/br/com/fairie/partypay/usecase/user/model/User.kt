package br.com.fairie.partypay.usecase.user.model

import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo

data class User(
    val id: Long,
    val name: String,
    val username: String,
    val email: Email,
    val secret: String,
    val phone: Phone,
    val photo: Photo?,
    val profiles: MutableCollection<Profile>
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + secret.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + (photo?.hashCode() ?: 0)
        result = 31 * result + profiles.hashCode()
        return result
    }

}
