package br.com.fairie.partypay.usecase.authentication

import br.com.fairie.partypay.usecase.authentication.model.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.model.LoginData
import br.com.fairie.partypay.usecase.user.model.User

interface AuthService {
    fun generateToken(login: LoginData): GeneratedToken
    fun verifyToken(token: String): Boolean
    fun getUserIdFromToken(token: String): Long
    fun getUserById(userId: Long): User
}
