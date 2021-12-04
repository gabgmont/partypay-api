package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrderStatus
import br.com.fairie.partypay.usecase.session.vo.SessionResume
import br.com.fairie.partypay.vo.CPF

interface SessionUseCase {

    fun createSession(session: Session) : Session
    fun getSession(sessionId: Long): Session
    fun addUser(sessionId: Long, cpf: CPF): Session
    fun addOrder(sessionId: Long, orderName: String, cpfs: List<CPF>): Session
    fun updateOrderStatus(sessionId: Long, sessionOrderId: Long, status: SessionOrderStatus): Session
    fun endSession(sessionId: Long, forceClose: Boolean): SessionResume
}