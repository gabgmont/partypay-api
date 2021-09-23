package br.com.fairie.partypay.repositories.session.jpa

import br.com.fairie.partypay.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderEntity, Long>