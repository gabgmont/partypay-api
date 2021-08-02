package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.usecase.user.entity.User

interface UserRepository {
    fun findUserByCpf(cpf: CPF): User
}