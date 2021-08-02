package br.com.fairie.partypay.user.repository

import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.user.dao.UserDao.Companion.toUserVo
import br.com.fairie.partypay.usecase.user.entity.User
import br.com.fairie.partypay.user.testobj.UserRepoTest

class UserRepositoryImpl : UserRepository {

    companion object{
        const val USER_NOT_FOUND = "User not found."
    }

    override fun findUserByCpf(cpf: CPF): User {
        val user = when (cpf.value) {
            "11111111111" -> UserRepoTest.user1
            "22222222222" -> UserRepoTest.user2
            "33333333333" -> UserRepoTest.user3
            "44444444444" -> UserRepoTest.user4
            "55555555555" -> UserRepoTest.user5
            else -> throw NotFoundException(USER_NOT_FOUND)
        }

        return user.toUserVo()
    }
}