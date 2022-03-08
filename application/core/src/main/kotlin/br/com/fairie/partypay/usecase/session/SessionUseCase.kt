package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.session.model.Session
import br.com.fairie.partypay.usecase.session.model.SessionOrderStatus
import br.com.fairie.partypay.usecase.session.model.SessionResume
import br.com.fairie.partypay.vo.CPF

interface SessionUseCase {

    fun createSession(menuId: Long, table: Int, cpfs: List<CPF>): Session
    fun getSession(sessionId: Long): Session
    fun checkUserOnline(cpf: CPF): Session
    fun addUser(sessionId: Long, cpfs: List<CPF>): Session
    fun addOrder(sessionId: Long, orderId: Long, cpfs: List<CPF>): Session
    fun updateOrderStatus(sessionId: Long, sessionOrderId: Long, status: SessionOrderStatus): Session
    fun getResume(sessionId: Long): SessionResume
    fun endSession(sessionId: Long, forceClose: Boolean): SessionResume
}
