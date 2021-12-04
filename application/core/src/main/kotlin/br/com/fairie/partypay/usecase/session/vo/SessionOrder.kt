package br.com.fairie.partypay.usecase.session.vo

import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.user.vo.User

class SessionOrder(
    private val id: Long? = null,
    val order: Order,
    var status: SessionOrderStatus,
    val users: List<User>
){
    fun id() = id
}