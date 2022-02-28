package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrderStatus
import br.com.fairie.partypay.usecase.session.vo.SessionResume
import br.com.fairie.partypay.vo.CPF

interface SessionUseCase {

    fun createSession(session: Session): Session
    fun getSession(sessionId: Long): Session
    fun addUser(sessionId: Long, cpfs: List<CPF>): Session
    fun addOrder(sessionId: Long, orderName: String, cpfs: List<CPF>): Session
    fun updateOrderStatus(sessionId: Long, sessionOrderId: Long, status: SessionOrderStatus): Session
    fun getResume(sessionId: Long): SessionResume
    fun endSession(sessionId: Long, forceClose: Boolean): SessionResume
}
