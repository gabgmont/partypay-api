package br.com.fairie.partypay.usecase.session.vo

import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.user.vo.User

class SessionOrder(
    val order: Order,
    val users: List<User>
)