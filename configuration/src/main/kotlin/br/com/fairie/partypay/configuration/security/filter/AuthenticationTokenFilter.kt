package br.com.fairie.partypay.configuration.security.filter

import br.com.fairie.partypay.entity.UserEntity.Companion.toEntity
import br.com.fairie.partypay.usecase.authentication.AuthService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationTokenFilter(private val authService: AuthService) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        val token = retrieveToken(request)
        val isValid = authService.verifyToken(token!!)
        if(isValid){
            authenticateUser(token)
        }
        filterChain.doFilter(request, response)
    }

    private fun authenticateUser(token: String) {
        val userId = authService.getUserIdFromToken(token)
        val user = authService.getUserById(userId).toEntity()

        val authenticatedUser = UsernamePasswordAuthenticationToken(user, user.secret, user.authorities)
        SecurityContextHolder.getContext().authentication = authenticatedUser
    }

    private fun retrieveToken(request: HttpServletRequest): String {
        val token = request.getHeader("Authorization")

        if (token.isNullOrEmpty() || !token.startsWith("Bearer ")) return ""

        return token.substringAfter("Bearer ")
    }
}