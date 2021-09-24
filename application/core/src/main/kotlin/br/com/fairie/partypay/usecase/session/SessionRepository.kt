package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrder

interface SessionRepository {
    fun newSession(session: Session): Session
    fun updateSessionUser(session: Session): Session
    fun updateSessionOrder(session: Session, sessionOrder: SessionOrder): Session
    fun getSessionWithId(id: Long): Session
    fun getSessionsWithCounter(counter: Int): List<Session>
    fun getSessions(): List<Session>
}