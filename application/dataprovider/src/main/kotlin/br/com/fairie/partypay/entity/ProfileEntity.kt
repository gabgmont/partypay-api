package br.com.fairie.partypay.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ProfileEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String
) : GrantedAuthority {

    override fun getAuthority(): String = name
}