package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.model.User

interface UserRepository{

    fun registerUser(user: User): User
    fun findUserByUsername(username: String?): List<User>
    fun findUserByEmail(email: String) : List<User>
    fun findUserById(userId: Long): User
}
