package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.usecase.user.entity.User
import br.com.fairie.partypay.vo.CPF

interface UserRepository{

    fun findByCpf(cpf: CPF): List<User>
}