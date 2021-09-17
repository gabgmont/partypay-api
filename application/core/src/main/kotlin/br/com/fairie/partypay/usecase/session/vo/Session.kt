package br.com.fairie.partypay.usecase.session.vo

import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order

class Session(
    val id: Long,
    val restaurant: String,
    val table: Int,
    val menu: Menu,
    var status: SessionStatus,
    val users: MutableList<SessionUser>,
    val orders: MutableList<Order>
)