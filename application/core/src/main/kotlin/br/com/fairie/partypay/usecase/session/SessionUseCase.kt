package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionResume
import br.com.fairie.partypay.usecase.user.vo.User

interface SessionUseCase {

    fun createSession(session: Session) : Session
    fun addUser(sessionId: Long, user: User): Session
    fun addOrder(sessionId: Long, order: Order, users: List<User>): Session
    fun endSession(sessionId: Long): SessionResume
}