package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.session.model.Session
import br.com.fairie.partypay.usecase.session.model.SessionOrderStatus
import br.com.fairie.partypay.usecase.session.model.SessionResume

interface SessionUseCase {

    fun createSession(menuId: Long, table: Int, usernames: List<String>): Session
    fun getSession(sessionId: Long): Session
    fun checkUserOnline(username: String): Session
    fun addUser(sessionId: Long, usernames: List<String>): Session
    fun addOrder(sessionId: Long, orderId: Long, usernames: List<String>): Session
    fun updateOrderStatus(sessionId: Long, sessionOrderId: Long, status: SessionOrderStatus): Session
    fun getResume(sessionId: Long): SessionResume
    fun endSession(sessionId: Long, forceClose: Boolean): SessionResume
}
