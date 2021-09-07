package br.com.fairie.partypay.configuration.security.service

import br.com.fairie.partypay.configuration.security.mapper.toUsernamePasswordAuthToken
import br.com.fairie.partypay.entity.UserEntity
import br.com.fairie.partypay.entity.UserEntity.Companion.toEntity
import br.com.fairie.partypay.exception.BadRequestException
import br.com.fairie.partypay.usecase.authentication.AuthService
import br.com.fairie.partypay.usecase.authentication.vo.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.vo.LoginData
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.entity.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
) : UserDetailsService, AuthService {

    private lateinit var authManager: AuthenticationManager

    @Value("\${partypay.jwt.expiration}")
    private lateinit var jwtExpiration: String

    @Value("\${partypay.jwt.secret}")
    private lateinit var jwtSecret: String


    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findUserByEmail(username).toEntity()
    }

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

    override fun verifyToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
            true
        } catch (exception: Exception) {
            false
        }
    }

    override fun getUserIdFromToken(token: String): Long {
        val body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body

        return body.subject.toLong()

    }

    override fun getUserById(userId: Long): User {
        return userRepository.findUserById(userId)
    }

    fun setAuthManager(authManager: AuthenticationManager) {
        this.authManager = authManager
    }

}