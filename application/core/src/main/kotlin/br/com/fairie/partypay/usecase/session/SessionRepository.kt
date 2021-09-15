package br.com.fairie.partypay.usecase.session

import br.com.fairie.partypay.usecase.session.vo.Session

interface SessionRepository {
    fun newSession(session: Session): Session
    fun updateSession(session: Session): Session
    fun getSessionById(id: Long): Session
    fun getSessionsByTable(table: Int): List<Session>
    fun getSessions(): List<Session>
}