package br.com.fairie.partypay.usecase.session.model

import java.math.BigDecimal

data class SessionResume(
    val users: List<SessionUser>,
    val status: SessionStatus,
    val check: BigDecimal
)
