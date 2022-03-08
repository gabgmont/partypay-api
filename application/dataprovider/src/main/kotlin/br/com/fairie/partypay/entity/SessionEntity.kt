package br.com.fairie.partypay.entity

import br.com.fairie.partypay.usecase.session.model.SessionStatus
import javax.persistence.*

@Entity(name = "session_tbl")
class SessionEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val restaurant: String,

    @Column
    val menuId: Long,

    @Column
    val counter: Int,

    @Column
    @Enumerated(EnumType.STRING)
    val status: SessionStatus,

    @Column
    @OneToMany
    val orders: MutableList<SessionOrderEntity>,

    @ManyToMany(fetch = FetchType.EAGER)
    val users: List<UserEntity>
)
