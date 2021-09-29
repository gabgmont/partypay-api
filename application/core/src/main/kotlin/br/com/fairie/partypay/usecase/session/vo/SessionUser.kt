package br.com.fairie.partypay.usecase.session.vo

import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.user.vo.User
import java.math.BigDecimal

class SessionUser(
    val user: User,
    val orders: MutableList<Order>,
    var totalValue: BigDecimal
)