package br.com.fairie.partypay.usecase.session.vo

import br.com.fairie.partypay.usecase.menu.vo.Menu

class Session(
    val id: Long,
    val restaurant: String,
    val table: Int,
    val menu: Menu,
    var status: SessionStatus,
    val users: MutableList<SessionUser>,
    val orders: MutableList<SessionOrder>
)