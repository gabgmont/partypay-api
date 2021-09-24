package br.com.fairie.partypay.entity

import javax.persistence.*

@Entity(name = "menu_tbl")
class MenuEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column
    var restaurant: String,

    @Column
    @OneToMany(mappedBy = "name")
    var menu: List<CategoryEntity>
)