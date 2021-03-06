package br.com.fairie.partypay.usecase.authentication.impl

import br.com.fairie.partypay.usecase.authentication.AuthService
import br.com.fairie.partypay.usecase.authentication.AuthenticationUseCase
import br.com.fairie.partypay.usecase.authentication.model.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.model.LoginData

class AuthenticationUseCaseImpl(
    private val authenticationService: AuthService
) : AuthenticationUseCase {

    override fun authenticate(login: LoginData): GeneratedToken {
        return authenticationService.generateToken(login)
    }
}
