package br.com.fairie.partypay.usecase.authentication

import br.com.fairie.partypay.usecase.authentication.model.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.model.LoginData

interface AuthenticationUseCase {
    fun authenticate(login: LoginData): GeneratedToken
}
