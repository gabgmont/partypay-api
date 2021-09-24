package br.com.fairie.partypay.usecase.session.vo

import br.com.fairie.partypay.usecase.user.vo.User

class Session(
    private val id: Long,
    val restaurant: String,
    val table: Int,
    var status: SessionStatus,
    val users: MutableList<User>,
    val orders: MutableList<SessionOrder>
){
    fun id() = id
}