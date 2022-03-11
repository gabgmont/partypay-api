package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.model.User

interface UserUseCase {
     fun get(username: String?): List<User>
     fun register(user: User): User
}
