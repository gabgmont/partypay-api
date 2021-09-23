package br.com.fairie.partypay.endpoints.menu.dto

import java.math.BigDecimal

class OrderDTO(
    val name: String,
    val description: String,
    val value: BigDecimal
)