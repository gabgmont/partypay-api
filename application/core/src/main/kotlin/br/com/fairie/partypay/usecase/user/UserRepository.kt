package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.model.User
import br.com.fairie.partypay.vo.Email

interface UserRepository{

    fun registerUser(user: User): User
    fun findUserByUsernameOrEmail(username: String?): List<User>
    fun findUserByEmail(email: Email) : List<User>
    fun findUserByUsername(username: String?): List<User>
    fun findUserById(userId: Long): User
}
