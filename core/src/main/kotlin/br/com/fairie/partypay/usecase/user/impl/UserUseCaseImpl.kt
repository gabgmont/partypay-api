package br.com.fairie.partypay.usecase.user.impl

import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.usecase.user.entity.User

class UserUseCaseImpl(
    private val repository: UserRepository
): UserUseCase {
    override fun get(cpf: CPF): User {
        return repository.findUserByCpf(cpf)
    }
}