package br.com.fairie.partypay.usecase.session.vo

import java.math.BigDecimal

class SessionResume(
    val users: List<SessionUser>,
    val check: BigDecimal
)