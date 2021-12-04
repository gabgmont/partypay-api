package br.com.fairie.partypay.repositories.menu.db.repository

import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.repositories.menu.db.jpa.OrderJpaRepository
import br.com.fairie.partypay.repositories.menu.db.mapper.toEntity
import br.com.fairie.partypay.repositories.menu.db.mapper.toModel
import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.menu.vo.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuRepositoryImpl : MenuRepository {

    @Autowired
    private lateinit var orderJpaRepository: OrderJpaRepository

    override fun getOrderByName(orderName: String): Order {
        try{
            val orderEntity = orderJpaRepository.findOrderEntityByName(orderName)
            return orderEntity.toModel()
        }catch (exception: Exception){
            throw NotFoundException("Order $orderName not found.")
        }
    }

    override fun saveOrder(order: Order) {
        val entity = order.toEntity()
        orderJpaRepository.save(entity)
    }
}