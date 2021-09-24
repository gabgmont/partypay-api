package br.com.fairie.partypay.usecase.session.impl

import br.com.fairie.partypay.exception.EmptyListException
import br.com.fairie.partypay.exception.SessionCreationException
import br.com.fairie.partypay.exception.SessionStatusException
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
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.vo.CPF

class SessionUseCaseImpl(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val menuJsonRepository: MenuJsonRepository
) : SessionUseCase {

    override fun createSession(session: Session): Session {
        val sessionsByTable = sessionRepository.getSessionsWithCounter(session.table)

        if (sessionsByTable.isNotEmpty())
            sessionsByTable.forEach { instance ->
                if (instance.isOpen()) throw SessionCreationException("Session is already open on this table.")
            }

        return sessionRepository.newSession(session)
    }

    override fun addUser(sessionId: Long, cpf: CPF): Session {
        val session = sessionRepository.getSessionWithId(sessionId)
        if (session.isClosed()) throw SessionStatusException("Session is already closed.")

        val users = userRepository.findUser(cpf)
        if (users.isEmpty()) throw EmptyListException("No users found with provided CPF.")

        session.users.add(users.first())
        return sessionRepository.updateSessionUser(session)
    }

    override fun addOrder(sessionId: Long, orderName: String, cpfs: List<CPF>): Session {
        val session = sessionRepository.getSessionWithId(sessionId)
        if (session.isClosed()) throw SessionStatusException("Session is already closed.")

        val order = menuJsonRepository.getOrderByName(session.restaurant, orderName)

        val userList = ArrayList<User>()
        cpfs.forEach { cpf ->
            try {
                val users = userRepository.findUser(cpf)
                userList.add(users.first())

            } catch (exception: Exception) {

            }
        }

        val sessionOrder = SessionOrder(0, order, userList)
        return sessionRepository.updateSessionOrder(session, sessionOrder)
    }

    override fun endSession(sessionId: Long): SessionResume {
        val session = sessionRepository.getSessionWithId(sessionId)
        val sessionResume = session.calculateSessionResume()

        session.close()
        sessionRepository.updateSessionUser(session)
        return sessionResume
    }
}