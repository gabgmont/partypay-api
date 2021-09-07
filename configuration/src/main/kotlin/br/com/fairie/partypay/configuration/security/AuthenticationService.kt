package br.com.fairie.partypay.configuration.security

import br.com.fairie.partypay.entity.UserEntity.Companion.toEntity
import br.com.fairie.partypay.usecase.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
) : UserDetailsService{


    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findUserByEmail(username).toEntity()
    }


}