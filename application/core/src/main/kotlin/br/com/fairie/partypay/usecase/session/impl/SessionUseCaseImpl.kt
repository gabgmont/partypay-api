package br.com.fairie.partypay.usecase.session.impl

import br.com.fairie.partypay.exception.SessionCreationException
import br.com.fairie.partypay.exception.SessionStatusException
import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.session.SessionRepository
import br.com.fairie.partypay.usecase.session.SessionUseCase
import br.com.fairie.partypay.usecase.session.vo.Session
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

    override fun endSession(session: Session): Session {
        val currentSession = repository.getSessionById(session.id)

        currentSession.close()
        return repository.updateSession(currentSession)
    }

    override fun addUser(session: Session, user: User): Session {
        val currentSession = repository.getSessionById(session.id)

        if (currentSession.isClosed()) throw SessionStatusException("Session is already closed.")

        currentSession.users.add(user)
        return repository.updateSession(session)
    }

    override fun addOrder(session: Session, order: Order): Session {
        val currentSession = repository.getSessionById(session.id)

        if (currentSession.isClosed()) throw SessionStatusException("Session is already closed.")

        currentSession.orders.add(order)
        return repository.updateSession(session)
    }
}