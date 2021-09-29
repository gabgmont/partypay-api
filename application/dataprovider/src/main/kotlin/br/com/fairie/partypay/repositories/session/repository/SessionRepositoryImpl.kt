package br.com.fairie.partypay.repositories.session.repository

import br.com.fairie.partypay.repositories.menu.db.jpa.OrderJpaRepository
import br.com.fairie.partypay.repositories.session.jpa.SessionJpaRepository
import br.com.fairie.partypay.repositories.session.jpa.SessionOrderJpaRepository
import br.com.fairie.partypay.repositories.session.mapper.toEntity
import br.com.fairie.partypay.repositories.session.mapper.toSession
import br.com.fairie.partypay.repositories.session.mapper.toSessionEntity
import br.com.fairie.partypay.repositories.session.mapper.toSessionList
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SessionRepositoryImpl : SessionRepository {

    @Autowired
    private lateinit var sessionJpaRepository: SessionJpaRepository

    @Autowired
    private lateinit var sessionOrderJpaRepository: SessionOrderJpaRepository

    @Autowired
    private lateinit var orderJpaRepository: OrderJpaRepository

    override fun newSession(session: Session): Session {
        val entity = session.toSessionEntity()
        sessionJpaRepository.save(entity)
        return session
    }

    override fun updateSessionOrder(session: Session, sessionOrder: SessionOrder): Session {
        var sessionEntity = session.toSessionEntity()
        var sessionOrderEntity = sessionOrder.toEntity()

        val orderEntity = try{
            orderJpaRepository.findOrderEntityByName(sessionOrderEntity.order.name)

        }catch (exception: Exception){
            orderJpaRepository.save(sessionOrderEntity.order)
        }
        sessionOrderEntity.order = orderEntity
        sessionOrderEntity = sessionOrderJpaRepository.save(sessionOrderEntity)
        sessionEntity.orders.add(sessionOrderEntity)
        sessionEntity = sessionJpaRepository.save(sessionEntity)
        return sessionEntity.toSession()
    }

    override fun updateSessionUser(session: Session): Session {
        val sessionEntity = session.toSessionEntity()

        sessionJpaRepository.save(sessionEntity)
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