package br.com.fairie.partypay.entity

import javax.persistence.*

@Entity(name = "menu_tbl")
class MenuEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var restaurant: String,
    @OneToMany(mappedBy = "name")
    var menu: List<CategoryEntity>
)