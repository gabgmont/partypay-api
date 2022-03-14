package br.com.fairie.partypay.usecase.session.impl

import br.com.fairie.partypay.exception.InconsistenceException
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.exception.PendingOrdersException
import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.SessionUseCase
import br.com.fairie.partypay.usecase.session.mapper.calculateSessionResume
import br.com.fairie.partypay.usecase.session.mapper.close
import br.com.fairie.partypay.usecase.session.mapper.isClosed
import br.com.fairie.partypay.usecase.session.mapper.isOpen
import br.com.fairie.partypay.usecase.session.model.*
import br.com.fairie.partypay.usecase.user.UserRepository
import java.math.BigDecimal

class SessionUseCaseImpl(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository
) : SessionUseCase {

    override fun createSession(menuId: Long, table: Int, usernames: List<String>): Session {
        val menu = menuRepository.getMenuById(menuId)
        val sessionsByTable = sessionRepository.getSessionsWithCounter(table)

        if (sessionsByTable.isNotEmpty())
            sessionsByTable.forEach { instance ->
                if (instance.isOpen()) throw InconsistenceException("Session is already open on this table.")
            }

        val users = usernames.map { username ->
            val users = userRepository.findUserByUsernameOrEmail(username)
            if (users.isEmpty()) throw NotFoundException("User ${username} not found.")

           return@map users.first()
        } as MutableList

        val session = Session(0, menu.name, menuId, table, SessionStatus.OPEN, users, arrayListOf())
        return sessionRepository.newSession(session)
    }

    override fun getSession(sessionId: Long): Session {
        return sessionRepository.getSessionWithId(sessionId)
    }

    override fun checkUserOnline(username: String): Session {
        val users = userRepository.findUserByUsernameOrEmail(username)
        if (users.isEmpty()) throw NotFoundException("User ${username} not found.")

        val sessions = sessionRepository.getOpenSessions()
        val user = users.first()
        val userSessions = sessions.filter { session -> session.users.contains(user) }

        if (userSessions.isEmpty()) throw NotFoundException("User ${user.username} is not in any open session.")
        if (userSessions.size > 1) throw InconsistenceException("Multiple sessions for user ${user.username}")

        return userSessions.first()
    }

    override fun addUser(sessionId: Long, usernames: List<String>): Session {
        val session = sessionRepository.getSessionWithId(sessionId)
        if (session.isClosed()) throw InconsistenceException("Session is already closed.")

        usernames.forEach { username ->
            val users = userRepository.findUserByUsernameOrEmail(username)
            if (users.isEmpty()) throw NotFoundException("User ${username} not found.")

            val user = users.first()
            if (session.users.contains(user)) throw InconsistenceException("User ${user.username} already in session.")

            session.users.add(users.first())
        }
        return sessionRepository.updateSession(session)
    }

    override fun addOrder(sessionId: Long, orderId: Long, usernames: List<String>): Session {
        val session = sessionRepository.getSessionWithId(sessionId)
        if (session.isClosed()) throw InconsistenceException("Session is already closed.")

        val order = menuRepository.getOrderById(orderId)

        val userList = usernames.map { username ->
            val users = userRepository.findUserByUsernameOrEmail(username)
            if (users.isEmpty()) throw NotFoundException("User ${username} is not on current session.")

            session.users.find { user ->
                user.username == username
            } ?: throw NotFoundException("User ${username} not on current session.")

            return@map users.first()
        }

        val sessionOrder = SessionOrder(null, order, SessionOrderStatus.DELIVERED, userList)
        return sessionRepository.addSessionOrder(session, sessionOrder)
    }

    override fun updateOrderStatus(sessionId: Long, sessionOrderId: Long, status: SessionOrderStatus): Session {
        val session = sessionRepository.getSessionWithId(sessionId)

        val sessionOrder = session.orders.find { sessionOrder ->
            sessionOrder.id == sessionOrderId
        } ?: throw NotFoundException("Order $sessionOrderId not found in this session.")

        sessionOrder.status = status
        return sessionRepository.updateSessionOrder(session, sessionOrder)

    }

    override fun getResume(sessionId: Long): SessionResume {
        val session = sessionRepository.getSessionWithId(sessionId)
        val sessionUserList = session.users.map { user ->
            SessionUser(
                user = user,
                orders = arrayListOf(),
                totalValue = BigDecimal.ZERO
            )
        }

        return session.calculateSessionResume(sessionUserList)
    }

    override fun endSession(sessionId: Long, forceClose: Boolean): SessionResume {
        val session = sessionRepository.getSessionWithId(sessionId)

        if (forceClose) session.orders.forEach { order ->
            if (order.status != SessionOrderStatus.DELIVERED) order.status = SessionOrderStatus.CANCELED
        }

        val notDeliveredOrders = session.orders.filter { order ->
            order.status != SessionOrderStatus.DELIVERED && order.status != SessionOrderStatus.CANCELED
        }
        if (notDeliveredOrders.isNotEmpty()) throw PendingOrdersException(
            "There are pending orders in this session.",
            notDeliveredOrders
        )


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
