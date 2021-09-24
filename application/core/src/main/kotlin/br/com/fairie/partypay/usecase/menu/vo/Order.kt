package br.com.fairie.partypay.usecase.menu.vo

import java.math.BigDecimal

class Order(
    private val id: Long,
    val name: String,
    val description: String,
    val value: BigDecimal
){
    fun id() = id
}