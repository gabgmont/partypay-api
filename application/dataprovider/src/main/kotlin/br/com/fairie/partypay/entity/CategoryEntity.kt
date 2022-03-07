package br.com.fairie.partypay.entity

import javax.persistence.*

@Entity(name = "category_tbl")
class CategoryEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column
    val name: String,

    @Column
    @OneToMany(fetch = FetchType.EAGER)
    val orders: List<OrderEntity>
)
