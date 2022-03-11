package br.com.fairie.partypay.usecase.user.impl

import br.com.fairie.partypay.exception.InconsistenceException
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.usecase.user.model.User

class UserUseCaseImpl(
        private val repository: UserRepository
) : UserUseCase {
    override fun get(username: String?): List<User> {
        val users = repository.findUserByUsername(username)
        if (users.isEmpty()) throw NotFoundException("User $username not found")

        return users
    }

    override fun register(user: User): User {

        val users = repository.findUserByUsername(user.username)
        if (users.isNotEmpty()) throw InconsistenceException("User ${user.username} already registered")

        return repository.registerUser(user)
    }
}
