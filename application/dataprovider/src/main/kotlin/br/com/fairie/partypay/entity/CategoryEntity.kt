package br.com.fairie.partypay.entity

import javax.persistence.*

@Entity(name = "category_tbl")
class CategoryEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    val name: String,
    @OneToMany(mappedBy = "name")
    val orders: List<OrderEntity>
)