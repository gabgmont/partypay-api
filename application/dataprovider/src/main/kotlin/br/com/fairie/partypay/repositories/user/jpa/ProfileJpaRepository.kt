package br.com.fairie.partypay.repositories.user.jpa

import br.com.fairie.partypay.entity.ProfileEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileJpaRepository : JpaRepository<ProfileEntity, Long> {
    fun getByName(name: String): ProfileEntity
}
