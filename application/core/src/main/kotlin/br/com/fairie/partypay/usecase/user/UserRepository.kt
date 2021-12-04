package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.vo.CPF

interface UserRepository{

    fun registerUser(user: User): User
    fun findUser(cpf: CPF?): List<User>
    fun findUserByEmail(email: String) : List<User>
    fun findUserById(userId: Long): User
}