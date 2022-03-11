package br.com.fairie.partypay.entity

import br.com.fairie.partypay.usecase.user.model.Profile
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity(name = "profile_tbl")
class ProfileEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val name: String

) : GrantedAuthority {

    override fun getAuthority(): String = name

    companion object {
        fun Profile.toEntity() =
            ProfileEntity(
                id = id,
                name = type.name
            )
    }
}
