package br.com.fairie.partypay.entity

import javax.persistence.*

@Entity(name = "session_order_tbl")
class SessionOrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @ManyToOne
    val order: OrderEntity,

    @ManyToMany(fetch = FetchType.EAGER)
    val users: List<UserEntity>

)