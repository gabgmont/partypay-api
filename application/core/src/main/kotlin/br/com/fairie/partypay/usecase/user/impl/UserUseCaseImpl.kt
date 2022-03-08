package br.com.fairie.partypay.usecase.user.impl

import br.com.fairie.partypay.exception.InconsistenceException
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.usecase.user.model.User

class UserUseCaseImpl(
        private val repository: UserRepository
) : UserUseCase {
    override fun get(cpf: CPF?): List<User> {
        val users = repository.findUser(cpf)
        if (users.isEmpty()) throw NotFoundException("User ${cpf?.value ?: ""} not found")

        return users
    }

    override fun register(user: User): User {

        val users = repository.findUser(user.cpf)
        if (users.isNotEmpty()) throw InconsistenceException("User ${user.cpf.value} already registered")

        return repository.registerUser(user)
    }
}
