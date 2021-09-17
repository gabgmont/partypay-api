package br.com.fairie.partypay.usecase.session.impl

import br.com.fairie.partypay.exception.EmptyListException
import br.com.fairie.partypay.exception.SessionCreationException
import br.com.fairie.partypay.exception.SessionStatusException
import br.com.fairie.partypay.usecase.menu.MenuRepository
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

class SessionUseCaseImpl(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository
): SessionUseCase {

    override fun createSession(session: Session): Session {
        val sessionsByTable = sessionRepository.getSessionsByTable(session.table)

        if (sessionsByTable.isNotEmpty())
            sessionsByTable.parallelStream().limit(20).forEach { instance ->
                if(instance.isOpen()) throw SessionCreationException("Session is already open on this table.")
            }

        return sessionRepository.newSession(session)
    }

    override fun addUser(sessionId: Long, cpf: CPF): Session {
        val session = sessionRepository.getSessionById(sessionId)
        val users = userRepository.findUser(cpf)

        if (users.isEmpty()) throw EmptyListException("No users found with provided CPF.")

        if (session.isClosed()) throw SessionStatusException("Session is already closed.")
        val newUser = SessionUser(users.first(), arrayListOf())

        session.users.add(newUser)
        return sessionRepository.updateSession(session)
    }

    override fun addOrder(sessionId: Long, orderName: String, cpfs: List<CPF>): Session {
        val session = sessionRepository.getSessionById(sessionId)

        if (session.isClosed()) throw SessionStatusException("Session is already closed.")

        val userList = ArrayList<User>()
        cpfs.forEach { cpf ->
            try {
                val users = userRepository.findUser(cpf)
                userList.add(users.first())

            }catch (exception: Exception){

            }
        }

        val order = menuRepository.getOrderByName(session.menu.name, orderName)
        val newOrder = SessionOrder(order, userList)

        userList.forEach { user ->
            session.users.forEach{ sessionUser ->
                if (user == sessionUser.user){
                    sessionUser.orders.add(order)
                    sessionUser.updateShare(cpfs.size)
                }
            }
        }

        session.orders.add(newOrder)
        return sessionRepository.updateSession(session)
    }

    override fun endSession(sessionId: Long): SessionResume {
        val session = sessionRepository.getSessionById(sessionId)

        val sessionResume = session.calculateSessionResume()

        session.close()
        sessionRepository.updateSession(session)
        return sessionResume
    }
}