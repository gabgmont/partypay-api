package br.com.fairie.partypay.repositories.user.jpa

import br.com.fairie.partypay.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository: JpaRepository<UserEntity, Long> {

    fun getUserEntityByUsernamed(username: String): List<UserEntity>
    fun getUserEntityByEmail(email: String) : List<UserEntity>
}
