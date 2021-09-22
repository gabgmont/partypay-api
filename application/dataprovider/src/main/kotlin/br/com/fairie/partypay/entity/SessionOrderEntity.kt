package br.com.fairie.partypay.entity

import javax.persistence.*

@Entity(name = "session_order_tbl")
class SessionOrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToMany(fetch = FetchType.LAZY)
    val user: List<UserEntity>,

    @ManyToOne
    val order: OrderEntity
)