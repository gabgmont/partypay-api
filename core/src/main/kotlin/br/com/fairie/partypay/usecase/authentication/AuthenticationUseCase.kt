package br.com.fairie.partypay.usecase.authentication

import br.com.fairie.partypay.usecase.authentication.vo.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.vo.LoginData

interface AuthenticationUseCase {
    fun authenticate(login: LoginData): GeneratedToken
}