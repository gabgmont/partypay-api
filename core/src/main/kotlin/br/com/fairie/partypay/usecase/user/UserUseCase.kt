package br.com.fairie.partypay.usecase.user

import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.usecase.user.entity.User

interface UserUseCase {
     fun get(cpf: CPF): List<User>
}