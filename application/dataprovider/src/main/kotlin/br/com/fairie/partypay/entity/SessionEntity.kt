package br.com.fairie.partypay.entity

import br.com.fairie.partypay.usecase.session.vo.SessionStatus
import javax.persistence.*

@Entity(name = "session_tbl")
class SessionEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val restaurant: String,
    val counter: Int,

    @Enumerated(EnumType.STRING)
    val status: SessionStatus,

    @OneToMany
    val orders: List<SessionOrderEntity>,

    @ManyToMany(fetch = FetchType.EAGER)
    val users: List<UserEntity>
)