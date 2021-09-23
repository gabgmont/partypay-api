package br.com.fairie.partypay.repositories.session.jpa

import br.com.fairie.partypay.entity.SessionOrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SessionOrderJpaRepository: JpaRepository<SessionOrderEntity, Long>