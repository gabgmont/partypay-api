package br.com.fairie.partypay.entity

import br.com.fairie.partypay.usecase.session.vo.SessionOrderStatus
import javax.persistence.*

@Entity(name = "session_order_tbl")
class SessionOrderEntity(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @ManyToOne
        var order: OrderEntity,

        @Column
        @Enumerated(EnumType.STRING)
        val status: SessionOrderStatus,

        @ManyToMany(fetch = FetchType.EAGER)
        val users: List<UserEntity>
)