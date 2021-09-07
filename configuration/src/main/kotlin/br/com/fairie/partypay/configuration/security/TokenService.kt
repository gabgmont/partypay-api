package br.com.fairie.partypay.configuration.security

import br.com.fairie.partypay.configuration.security.mapper.toUsernamePasswordAuthToken
import br.com.fairie.partypay.entity.UserEntity
import br.com.fairie.partypay.exception.BadRequestException
import br.com.fairie.partypay.usecase.authentication.AuthService
import br.com.fairie.partypay.usecase.authentication.vo.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.vo.LoginData
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenService(
    private val authManager: AuthenticationManager
) : AuthService {

    @Value("\${partypay.jwt.expiration}")
    private lateinit var jwtExpiration: String

    @Value("\${partypay.jwt.secret}")
    private lateinit var jwtSecret: String

    override fun generateToken(login: LoginData): GeneratedToken {
        val authUserPasswordToken = login.toUsernamePasswordAuthToken()
        try {
            val authenticated = authManager.authenticate(authUserPasswordToken)

            val user = authenticated.principal as UserEntity
            val now = Date()
            val expirationDate = Date(now.time + jwtExpiration.toLong())

            val token = Jwts.builder()
                .setIssuer("PartyPay! Api")
                .setSubject(user.id.toString())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact()

            return GeneratedToken(token, jwtExpiration)

        } catch (exception: AuthenticationException) {
            throw BadRequestException("Credenciais Inválidas")
        }
    }
}