package br.com.fairie.partypay.usecase.session.impl

import br.com.fairie.partypay.exception.InconsistenceException
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.exception.PendingOrdersException
import br.com.fairie.partypay.usecase.menu.MenuJsonRepository
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.SessionUseCase
import br.com.fairie.partypay.usecase.session.mapper.calculateSessionResume
import br.com.fairie.partypay.usecase.session.mapper.close
import br.com.fairie.partypay.usecase.session.mapper.isClosed
import br.com.fairie.partypay.usecase.session.mapper.isOpen
import br.com.fairie.partypay.usecase.session.vo.*
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.vo.CPF
import java.math.BigDecimal

class SessionUseCaseImpl(
        private val sessionRepository: SessionRepository,
        private val userRepository: UserRepository,
        private val menuJsonRepository: MenuJsonRepository
) : SessionUseCase {

    override fun createSession(session: Session): Session {
        menuJsonRepository.getMenuByName(session.restaurant)
        val sessionsByTable = sessionRepository.getSessionsWithCounter(session.table)

        if (sessionsByTable.isNotEmpty())
            sessionsByTable.forEach { instance ->
                if (instance.isOpen()) throw InconsistenceException("Session is already open on this table.")
            }

        return sessionRepository.newSession(session)
    }

    override fun getSession(sessionId: Long): Session {
        return sessionRepository.getSessionWithId(sessionId)
    }

    override fun addUser(sessionId: Long, cpf: CPF): Session {
        val session = sessionRepository.getSessionWithId(sessionId)
        if (session.isClosed()) throw InconsistenceException("Session is already closed.")

        val users = userRepository.findUser(cpf)
        if (users.isEmpty()) throw NotFoundException("User ${cpf.value} not found.")

        val user = users.first()
        if (session.users.contains(user)) throw InconsistenceException("User ${user.cpf.value} already in session.")

        session.users.add(users.first())
        return sessionRepository.updateSession(session)
    }

    override fun addOrder(sessionId: Long, orderName: String, cpfs: List<CPF>): Session {
        val session = sessionRepository.getSessionWithId(sessionId)
        if (session.isClosed()) throw InconsistenceException("Session is already closed.")

        val order = menuJsonRepository.getOrderByName(session.restaurant, orderName)

        val userList = cpfs.map { cpf ->
            val users = userRepository.findUser(cpf)
            if (users.isEmpty()) throw NotFoundException("User ${cpf.value} is not on current session.")

            session.users.find { user ->
                user.cpf.value == cpf.value
            } ?: throw NotFoundException("User ${cpf.value} not on current session.")

            return@map users.first()
        }

        val sessionOrder = SessionOrder(null, order, SessionOrderStatus.PENDING, userList)
        return sessionRepository.addSessionOrder(session, sessionOrder)
    }

    override fun updateOrderStatus(sessionId: Long, sessionOrderId: Long, status: SessionOrderStatus): Session {
        val session = sessionRepository.getSessionWithId(sessionId)

        val sessionOrder = session.orders.find { sessionOrder ->
            sessionOrder.id() == sessionOrderId
        } ?: throw NotFoundException("Order $sessionOrderId not found in this session.")

        sessionOrder.status = status
        return sessionRepository.updateSessionOrder(session, sessionOrder)

    }

    override fun endSession(sessionId: Long, forceClose: Boolean): SessionResume {
        val session = sessionRepository.getSessionWithId(sessionId)

        if (!forceClose){
            val notDeliveredOrders = session.orders.filter { order ->
                order.status != SessionOrderStatus.DELIVERED
            }
            if (notDeliveredOrders.isNotEmpty()) throw PendingOrdersException("There are pending orders in this session.", notDeliveredOrders)
        }

        val sessionUserList = session.users.map { user ->
            SessionUser(
                    user = user,
                    orders = arrayListOf(),
                    totalValue = BigDecimal.ZERO
            )
        }

        val sessionResume = session.calculateSessionResume(sessionUserList)

        session.close()
        sessionRepository.updateSession(session)
        return sessionResume
    }
}