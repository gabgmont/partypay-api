package br.com.fairie.partypay.usecase.authentication

import br.com.fairie.partypay.usecase.authentication.vo.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.vo.LoginData
import br.com.fairie.partypay.usecase.user.entity.User

interface AuthService {
    fun generateToken(login: LoginData): GeneratedToken
    fun verifyToken(token: String): Boolean
    fun getUserIdFromToken(token: String): Long
    fun getUserById(userId: Long): User
}