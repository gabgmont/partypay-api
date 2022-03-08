package br.com.fairie.partypay.usecase.session.model

import br.com.fairie.partypay.usecase.menu.model.Order
import java.math.BigDecimal

data class SessionResumeOrder(
    val order: Order,
    val valuePerUser: BigDecimal
)
