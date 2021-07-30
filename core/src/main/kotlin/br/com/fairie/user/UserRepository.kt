package br.com.fairie.user

import br.com.fairie.shared.vo.CPF
import br.com.fairie.user.entity.User

interface UserRepository {
    fun findUserByCpf(cpf: CPF): User
}