package br.com.fairie.partypay.usecase.session.model

import br.com.fairie.partypay.usecase.menu.model.Order
import br.com.fairie.partypay.usecase.user.model.User

data class SessionOrder(
    val id: Long? = null,
    val order: Order,
    var status: SessionOrderStatus,
    val users: List<User>
)
