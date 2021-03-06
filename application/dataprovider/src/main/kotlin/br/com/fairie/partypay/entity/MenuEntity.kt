package br.com.fairie.partypay.entity

import javax.persistence.*

@Entity(name = "menu_tbl")
class MenuEntity(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column
    var restaurant: String,

    @Column
    @OneToMany(fetch = FetchType.LAZY)
    var categories: List<CategoryEntity> = arrayListOf()
){
    constructor(id: Long, restaurant: String) : this(id, restaurant, arrayListOf())
}
