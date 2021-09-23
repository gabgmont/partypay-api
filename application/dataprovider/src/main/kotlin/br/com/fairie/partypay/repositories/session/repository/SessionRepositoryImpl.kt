package br.com.fairie.partypay.repositories.session.repository

import br.com.fairie.partypay.repositories.session.mapper.toSession
import br.com.fairie.partypay.repositories.session.mapper.toSessionEntity
import br.com.fairie.partypay.repositories.session.mapper.toSessionList
import br.com.fairie.partypay.repositories.session.jpa.OrderJpaRepository
import br.com.fairie.partypay.repositories.session.jpa.SessionJpaRepository
import br.com.fairie.partypay.repositories.session.jpa.SessionOrderJpaRepository
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.vo.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SessionRepositoryImpl: SessionRepository {

    @Autowired
    private lateinit var sessionJpaRepository: SessionJpaRepository

    @Autowired
    private lateinit var orderJpaRepository: OrderJpaRepository

    @Autowired
    private lateinit var sessionOrderJpaRepository: SessionOrderJpaRepository

    override fun newSession(session: Session): Session {
        val entity = session.toSessionEntity()
        sessionJpaRepository.save(entity)
        return session
    }

    override fun updateSession(session: Session): Session {
        val entity = session.toSessionEntity()

        entity.orders.forEach { orderSession ->
            orderJpaRepository.save(orderSession.order)
            sessionOrderJpaRepository.save(orderSession)
        }

        sessionJpaRepository.save(entity)
        return session
    }

    override fun getSessionWithId(id: Long): Session {
        val entity = sessionJpaRepository.findById(id).get()

        return entity.toSession()
    }

    override fun getSessionsWithCounter(counter: Int): List<Session> {
        val sessions = sessionJpaRepository.getSessionsByCounter(counter)

        return sessions.toSessionList()
    }

    override fun getSessions(): List<Session> {
        TODO("Not yet implemented")
    }
}