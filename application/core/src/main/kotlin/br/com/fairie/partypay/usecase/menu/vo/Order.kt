package br.com.fairie.partypay.usecase.menu.vo

import java.math.BigDecimal

class Order(
    val name: String,
    val description: String,
    val value: BigDecimal
)