package br.com.fairie.partypay.usecase.menu.model

data class Menu(
    val id: Long,
    val name: String,
    val categoryList: List<Category>
)
