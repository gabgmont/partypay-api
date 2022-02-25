package br.com.fairie.partypay.usecase.session.vo

import br.com.fairie.partypay.usecase.menu.vo.Order
import java.math.BigDecimal

class SessionResumeOrder(
    val order: Order,
    val valuePerUser: BigDecimal
)
