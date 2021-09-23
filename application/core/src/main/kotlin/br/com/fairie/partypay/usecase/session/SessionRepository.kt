package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.session.vo.Session

interface SessionRepository {
    fun newSession(session: Session): Session
    fun updateSession(session: Session): Session
    fun getSessionWithId(id: Long): Session
    fun getSessionsWithCounter(counter: Int): List<Session>
    fun getSessions(): List<Session>
}