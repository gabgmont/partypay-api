package br.com.fairie.partypay.repositories.user.repository

import br.com.fairie.partypay.entity.ProfileEntity
import br.com.fairie.partypay.entity.UserEntity.Companion.toEntity
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.repositories.session.mapper.toModel
import br.com.fairie.partypay.repositories.user.jpa.ProfileJpaRepository
import br.com.fairie.partypay.repositories.user.jpa.UserJpaRepository
import br.com.fairie.partypay.usecase.user.UserRepository
import br.com.fairie.partypay.usecase.user.model.User
import br.com.fairie.partypay.vo.Email
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserRepositoryImpl : UserRepository {

    @Autowired
    private lateinit var jpaRepository: UserJpaRepository

    @Autowired
    private lateinit var profileRepository: ProfileJpaRepository

    override fun registerUser(user: User): User {
        val profileEntities = arrayListOf<ProfileEntity>()

        user.profiles.forEach { profile ->
            profileEntities.add(profileRepository.getByName(profile.type.name))
        }
        var userEntity = user.toEntity()
        userEntity.profiles = profileEntities
        while (true){
            try {
                userEntity = jpaRepository.save(userEntity)
                break
            }catch (ignored: Exception){
                userEntity.usernamed += (1..999).random()
            }
        }
        return userEntity.toModel()
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

    override fun findUserByEmail(email: Email): List<User> {
        return try {
            val users = jpaRepository.getUserEntityByEmail(email.value)
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
