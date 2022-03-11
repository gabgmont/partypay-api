package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.model.User

interface UserUseCase {
     fun get(usernameOrEmail: String?): List<User>
     fun register(user: User): User
     fun socialRegister(user: User): User
}
