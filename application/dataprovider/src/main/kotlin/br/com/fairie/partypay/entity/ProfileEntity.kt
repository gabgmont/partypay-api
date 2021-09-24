package br.com.fairie.partypay.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
class ProfileEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val name: String

) : GrantedAuthority {

    override fun getAuthority(): String = name
}