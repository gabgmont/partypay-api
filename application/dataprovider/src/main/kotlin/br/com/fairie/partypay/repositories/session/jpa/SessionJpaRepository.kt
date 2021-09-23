package br.com.fairie.partypay.repositories.session.jpa

import br.com.fairie.partypay.entity.SessionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionJpaRepository: JpaRepository<SessionEntity, Long> {

    fun getSessionsByCounter(counter: Int): List<SessionEntity>
}