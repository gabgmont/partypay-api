package br.com.fairie.partypay.usecase.session.vo

import br.com.fairie.partypay.exception.SessionStatusException
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.user.vo.User

class Session(
    val id: Long,
    val restaurant: String,
    val table: Int,
    val menu: Menu,
    var status: SessionStatus,
    val users: MutableList<User>,
    val orders: MutableList<Order>
){
    fun isOpen(): Boolean = status == SessionStatus.OPEN
    fun isClosed(): Boolean = status == SessionStatus.CLOSED

    fun close(){
        if (isOpen()) this.status = SessionStatus.CLOSED
        else throw SessionStatusException("Session is already closed.")
    }
}