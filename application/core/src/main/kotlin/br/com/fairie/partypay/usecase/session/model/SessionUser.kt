package br.com.fairie.partypay.usecase.session.model

import br.com.fairie.partypay.usecase.user.model.User
import java.math.BigDecimal

data class SessionUser(
    val user: User,
    val orders: MutableList<SessionResumeOrder>,
    var totalValue: BigDecimal
)
