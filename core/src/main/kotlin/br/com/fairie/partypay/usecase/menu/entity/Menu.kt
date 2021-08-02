package br.com.fairie.partypay.usecase.menu.entity

class Menu(
    val id: Int,
    val name: String,
    val categoryList: List<Category>
)