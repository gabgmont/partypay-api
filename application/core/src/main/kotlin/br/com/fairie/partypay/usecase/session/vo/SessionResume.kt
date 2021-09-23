package br.com.fairie.partypay.usecase.session.vo

import java.math.BigDecimal

class SessionResume(
    val orders: MutableList<SessionOrder>,
    val check: BigDecimal
)