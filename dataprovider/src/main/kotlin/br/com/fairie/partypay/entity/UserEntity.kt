package br.com.fairie.partypay.entity

import br.com.fairie.partypay.usecase.user.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity(name = "users_tbl")
class UserEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val cpf: String,
    val email: String,
    val secret: String,
    val phone: String,
    val photo: String?,

    @ManyToMany(fetch = FetchType.EAGER)
    val profiles: MutableCollection<ProfileEntity>

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = profiles

    override fun getPassword(): String = secret

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    companion object {
        fun User.toEntity(): UserEntity {
            return UserEntity(
                id = id,
                name = name,
                cpf = cpf.value,
                email = email.value,
                secret = secret,
                phone = phone.value,
                photo = photo?.value ?: "",
                profiles = arrayListOf()
            )

            //TODO CONFIGURE PROFILES
        }
    }
}