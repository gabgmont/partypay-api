package br.com.fairie.partypay.usecase.menu.model

import java.math.BigDecimal

data class Order(
    val id: Long,
    val name: String,
    val description: String,
    val value: BigDecimal
)
