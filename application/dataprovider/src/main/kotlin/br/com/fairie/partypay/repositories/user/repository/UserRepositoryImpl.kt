package br.com.fairie.partypay.repositories.user.repository

import br.com.fairie.partypay.entity.UserEntity.Companion.toEntity
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.repositories.session.mapper.toModel
import br.com.fairie.partypay.repositories.user.jpa.UserJpaRepository
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserRepositoryImpl : UserRepository {

    @Autowired
    private lateinit var jpaRepository: UserJpaRepository

    override fun registerUser(user: User): User {
        jpaRepository.save(user.toEntity())
        return user
    }

    override fun findUserByUsername(username: String?): List<User> {
        return try {
            val users = if (username == null) jpaRepository.findAll()
            else jpaRepository.getUserEntityByUsernamed(username)

            users.map { userEntity -> userEntity.toModel() }

        } catch (exception: Exception) {
            arrayListOf()
        }
    }

    override fun findUserByEmail(email: String): List<User> {
        return try {
            val users = jpaRepository.getUserEntityByEmail(email)
            users.map { userEntity -> userEntity.toModel() }

        } catch (exception: Exception) {
            arrayListOf()
        }
    }

    override fun findUserById(userId: Long): User {
        try {
            return jpaRepository.getById(userId).toModel()
        } catch (exception: Exception) {
            throw NotFoundException("User not found with id $userId")
        }
    }
}
