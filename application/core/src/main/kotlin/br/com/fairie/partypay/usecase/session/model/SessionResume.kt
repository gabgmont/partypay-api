package br.com.fairie.partypay.usecase.session.model

import java.math.BigDecimal

data class SessionResume(
    val menuId: Long,
    val status: SessionStatus,
    val check: BigDecimal,
    val users: List<SessionUser>
)
