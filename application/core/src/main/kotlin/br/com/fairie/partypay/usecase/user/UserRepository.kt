package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.entity.User
import br.com.fairie.partypay.vo.CPF

interface UserRepository{

    fun findUser(cpf: CPF?): List<User>
    fun findUserByEmail(email: String?) : User
    fun findUserById(userId: Long): User
}