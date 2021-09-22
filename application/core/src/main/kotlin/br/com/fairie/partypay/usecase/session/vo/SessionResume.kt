package br.com.fairie.partypay.usecase.session.vo

import java.math.BigDecimal

class SessionResume(
    val users: List<SessionOrder>,
    val check: BigDecimal
)