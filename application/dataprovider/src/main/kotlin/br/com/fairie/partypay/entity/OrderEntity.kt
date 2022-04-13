package br.com.fairie.partypay.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "order_tbl")
class OrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val name: String,

    @Column
    val image: String,

    @Column(length = 500)
    val description: String,

    @Column
    val value: BigDecimal
)