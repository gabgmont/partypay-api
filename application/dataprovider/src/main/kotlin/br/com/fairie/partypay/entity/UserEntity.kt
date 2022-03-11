package br.com.fairie.partypay.entity

import br.com.fairie.partypay.usecase.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity(name = "users_tbl")
class UserEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val name: String,

    @Column
    val usernm: String,

    @Column
    val email: String,

    @Column
    val secret: String,

    @Column
    val phone: String,

    @Column
    val photo: String?,

    @ManyToMany(fetch = FetchType.EAGER)
    val profiles: MutableCollection<ProfileEntity>

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = profiles

    override fun getPassword(): String = secret

    override fun getUsername(): String = usernm

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    companion object {
        fun User.toEntity(): UserEntity {
            return UserEntity(
                id = id,
                name = name,
                usernm = username,
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
