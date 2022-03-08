package br.com.fairie.partypay.usecase.session.model

import br.com.fairie.partypay.usecase.user.model.User

data class Session(
    val id: Long,
    val restaurant: String,
    val table: Int,
    var status: SessionStatus,
    val users: MutableList<User>,
    val orders: MutableList<SessionOrder>
)
