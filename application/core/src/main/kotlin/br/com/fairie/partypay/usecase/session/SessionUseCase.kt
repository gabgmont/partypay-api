package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.user.vo.User

interface SessionUseCase {

    fun createSession(session: Session) : Session
    fun endSession(session: Session): Session
    fun addUser(session: Session, user: User): Session
    fun addOrder(session: Session, order: Order): Session
}