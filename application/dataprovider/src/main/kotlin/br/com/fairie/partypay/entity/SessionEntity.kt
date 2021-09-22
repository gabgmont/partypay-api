package br.com.fairie.partypay.entity

import javax.persistence.*

@Entity(name = "session_tbl")
class SessionEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val restaurant: String,
    val counter: String,
    val status: String,

    @OneToMany
    val users: List<SessionOrderEntity>,

    @ManyToMany(fetch = FetchType.LAZY)
    val orders: List<OrderEntity>
)