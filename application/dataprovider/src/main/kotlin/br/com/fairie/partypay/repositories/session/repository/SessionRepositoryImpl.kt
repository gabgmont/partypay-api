package br.com.fairie.partypay.repositories.session.repository

import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.exception.SQLCallException
import br.com.fairie.partypay.repositories.menu.db.jpa.OrderJpaRepository
import br.com.fairie.partypay.repositories.session.jpa.SessionJpaRepository
import br.com.fairie.partypay.repositories.session.jpa.SessionOrderJpaRepository
import br.com.fairie.partypay.repositories.session.mapper.toEntity
import br.com.fairie.partypay.repositories.session.mapper.toModel
import br.com.fairie.partypay.repositories.session.mapper.toModelList
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrder
import br.com.fairie.partypay.usecase.session.vo.SessionStatus
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
        var entity = session.toEntity()
        entity = sessionJpaRepository.save(entity)
        return entity.toModel()
    }

    override fun addSessionOrder(session: Session, sessionOrder: SessionOrder): Session {
        var sessionEntity = session.toEntity()
        var sessionOrderEntity = sessionOrder.toEntity()

        val orderEntity = try {
            orderJpaRepository.findOrderEntityByName(sessionOrderEntity.order.name)

        } catch (exception: Exception) {
            orderJpaRepository.save(sessionOrderEntity.order)
        }
        sessionOrderEntity.order = orderEntity
        sessionOrderEntity = sessionOrderJpaRepository.save(sessionOrderEntity)
        sessionEntity.orders.add(sessionOrderEntity)
        sessionEntity = sessionJpaRepository.save(sessionEntity)
        return sessionEntity.toModel()
    }

    override fun updateSessionOrder(session: Session, sessionOrder: SessionOrder): Session {
        try {
            val sessionOrderEntity = sessionOrder.toEntity()

            sessionOrderJpaRepository.save(sessionOrderEntity)
            return sessionJpaRepository.findById(session.id()).get().toModel()

        } catch (exception: Exception) {
            throw SQLCallException("Failed to cancel order ${sessionOrder.id()} - ${sessionOrder.order}")
        }
    }

    override fun updateSession(session: Session): Session {
        var sessionEntity = session.toEntity()

        sessionEntity = sessionJpaRepository.save(sessionEntity)
        return sessionEntity.toModel()
    }

    override fun getSessionWithId(id: Long): Session {
        try {
            val entity = sessionJpaRepository.findById(id).get()

            return entity.toModel()
        } catch (exception: Exception) {
            throw NotFoundException("Session with id $id not found.")
        }
    }

    override fun getSessionsWithCounter(counter: Int): List<Session> {
        try {
            val sessions = sessionJpaRepository.getSessionsByCounter(counter)

            return sessions.toModelList()
        } catch (exception: Exception) {
            throw NotFoundException("Session with table $counter not found")
        }
    }

    override fun getOpenSessions(): List<Session> {
        try {
            val sessions = sessionJpaRepository.getSessionEntitiesByStatus(SessionStatus.OPEN)
            return sessions.toModelList()

        }catch (exception: Exception){
            throw NotFoundException("All sessions are closed.")
        }
    }

    override fun getSessions(): List<Session> {
        TODO("Not yet implemented")
    }
}
