package br.com.fairie.partypay.usecase.user.impl

import br.com.fairie.partypay.exception.InconsistenceException
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.usecase.user.model.Profile
import br.com.fairie.partypay.usecase.user.model.User
import br.com.fairie.partypay.vo.Email

class UserUseCaseImpl(
    private val repository: UserRepository
) : UserUseCase {
    override fun get(usernameOrEmail: String?): List<User> {

        var users = listOf<User>()

        if(!usernameOrEmail.isNullOrBlank()) {
            users = repository.findUserByEmail(Email(usernameOrEmail))
        }

        if(users.isEmpty()) {
            users = repository.findUserByUsernameOrEmail(usernameOrEmail)
        }

        if (users.isEmpty()) throw NotFoundException("User $usernameOrEmail not found")
        return users
    }

    override fun register(user: User): User {

        var users = repository.findUserByEmail(user.email)
        if (users.isNotEmpty()) throw InconsistenceException("Email ${user.email.value} already registered")

        users = repository.findUserByUsernameOrEmail(user.username)
        if (users.isNotEmpty()) throw InconsistenceException("Username ${user.username} already registered")

        user.profiles.add(Profile(0, Profile.Type.STANDARD))
        return repository.registerUser(user)
    }

    override fun socialRegister(user: User): User {
        val users = repository.findUserByEmail(user.email)
        if (users.isNotEmpty()) throw InconsistenceException("Email ${user.email.value} already registered")

        user.profiles.add(Profile(0, Profile.Type.SOCIAL))
        return repository.registerUser(user)
    }
}
