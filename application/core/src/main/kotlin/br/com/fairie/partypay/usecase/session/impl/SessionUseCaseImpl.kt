package br.com.fairie.partypay.usecase.session.impl

import br.com.fairie.partypay.exception.SessionCreationException
import br.com.fairie.partypay.exception.SessionStatusException
import br.com.fairie.partypay.usecase.menu.vo.Order
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
import br.com.fairie.partypay.usecase.user.vo.User

class SessionUseCaseImpl(
    private val repository: SessionRepository
): SessionUseCase {

    override fun createSession(session: Session): Session {
        val sessionsByTable = repository.getSessionsByTable(session.table)

        if (sessionsByTable.isNotEmpty())
            sessionsByTable.parallelStream().limit(20).forEach { instance ->
                if(instance.isOpen()) throw SessionCreationException("Session is already open on this table.")
            }

        return repository.newSession(session)
    }

    override fun addUser(sessionId: Long, user: User): Session {
        val session = repository.getSessionById(sessionId)

        if (session.isClosed()) throw SessionStatusException("Session is already closed.")
        val newUser = SessionUser(user, arrayListOf())

        session.users.add(newUser)
        return repository.updateSession(session)
    }

    override fun addOrder(sessionId: Long, order: Order, users: List<User>): Session {
        val session = repository.getSessionById(sessionId)

        if (session.isClosed()) throw SessionStatusException("Session is already closed.")
        val newOrder = SessionOrder(order, users)

        users.forEach { user ->
            session.users.forEach{ sessionUser ->
                if (user == sessionUser.user){
                    sessionUser.orders.add(order)
                    sessionUser.updateShare(users.size)
                }
            }
        }

        session.orders.add(newOrder)
        return repository.updateSession(session)
    }

    override fun endSession(sessionId: Long): SessionResume {
        val session = repository.getSessionById(sessionId)

        val sessionResume = session.calculateSessionResume()

        session.close()
        repository.updateSession(session)
        return sessionResume
    }
}