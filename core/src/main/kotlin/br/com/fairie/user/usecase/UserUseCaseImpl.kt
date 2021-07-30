package br.com.fairie.user.usecase

import br.com.fairie.shared.vo.CPF
import br.com.fairie.user.UserRepository
import br.com.fairie.user.UserUseCase
import br.com.fairie.user.entity.User

class UserUseCaseImpl(
    private val repository: UserRepository
): UserUseCase {
    override fun get(cpf: CPF): User {
        return repository.findUserByCpf(cpf)
    }
}