package br.com.fairie.partypay.usecase.session.impl

import br.com.fairie.partypay.exception.InconsistenceException
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.usecase.menu.MenuJsonRepository
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.SessionUseCase
import br.com.fairie.partypay.usecase.session.mapper.calculateSessionResume
import br.com.fairie.partypay.usecase.session.mapper.close
import br.com.fairie.partypay.usecase.session.mapper.isClosed
import br.com.fairie.partypay.usecase.session.mapper.isOpen
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrder
import br.com.fairie.partypay.usecase.session.vo.SessionResume
import br.com.fairie.partypay.usecase.session.vo.SessionUser
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.vo.CPF
import java.math.BigDecimal

class SessionUseCaseImpl(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val menuJsonRepository: MenuJsonRepository
) : SessionUseCase {

    override fun createSession(session: Session): Session {
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
        return sessionRepository.addSessionUser(session)
    }

    override fun addOrder(sessionId: Long, orderName: String, cpfs: List<CPF>): Session {
        val session = sessionRepository.getSessionWithId(sessionId)
        if (session.isClosed()) throw InconsistenceException("Session is already closed.")

        val order = menuJsonRepository.getOrderByName(session.restaurant, orderName)
        val userList = ArrayList<User>()
        cpfs.forEach { cpf ->
            val users = userRepository.findUser(cpf)
            if (users.isEmpty()) throw NotFoundException("User ${cpf.value} is not on current session.")
            userList.add(users.first())
        }

        val sessionOrder = SessionOrder(0, order, userList)
        return sessionRepository.addSessionOrder(session, sessionOrder)
    }

    override fun cancelOrder(sessionId: Long, sessionOrderId: Long): Session {
        val session = sessionRepository.getSessionWithId(sessionId)
        val sessionOrder = session.orders.find { sessionOrder ->
            sessionOrder.id() == sessionOrderId
        } ?: throw NotFoundException("Order $sessionOrderId not found in this session.")

        return sessionRepository.cancelSessionOrder(session, sessionOrder)
    }

    override fun endSession(sessionId: Long): SessionResume {
        val session = sessionRepository.getSessionWithId(sessionId)

        val sessionUserList = ArrayList<SessionUser>()

        session.users.forEach { user ->
            sessionUserList.add(
                SessionUser(
                    user = user,
                    orders = arrayListOf(),
                    totalValue = BigDecimal.ZERO
                )
            )
        }

        val sessionResume = session.calculateSessionResume(sessionUserList)

        session.close()
        sessionRepository.addSessionUser(session)
        return sessionResume
    }
}