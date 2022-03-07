package br.com.fairie.partypay.repositories.menu.db.jpa

import br.com.fairie.partypay.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderEntity, Long>{

    fun findOrderEntityByName(orderName: String): OrderEntity
}
