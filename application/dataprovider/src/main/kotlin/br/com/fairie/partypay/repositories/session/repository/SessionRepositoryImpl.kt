package br.com.fairie.partypay.repositories.session.repository

import br.com.fairie.partypay.entity.SessionEntity
import br.com.fairie.partypay.repositories.session.mapper.toSession
import br.com.fairie.partypay.repositories.session.mapper.toSessionEntity
import br.com.fairie.partypay.repositories.session.mapper.toSessionList
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.vo.Session
import org.springframework.data.jpa.repository.JpaRepository

abstract class SessionRepositoryImpl: SessionRepository, JpaRepository<SessionEntity, Long> {

    override fun newSession(session: Session): Session {
        val entity = session.toSessionEntity()
        save(entity)
        return session
    }

    override fun updateSession(session: Session): Session {
        val entity = session.toSessionEntity()
        save(entity)
        return session
    }

    override fun getSessionWithId(id: Long): Session {
        val entity = findById(id).get()

        return entity.toSession()
    }

    override fun getSessionsWithCounter(counter: Int): List<Session> {
        val sessions = getSessionsByCounter(counter)

        return sessions.toSessionList()
    }

    override fun getSessions(): List<Session> {
        TODO("Not yet implemented")
    }

    abstract fun getSessionsByCounter(counter: Int): List<SessionEntity>
}