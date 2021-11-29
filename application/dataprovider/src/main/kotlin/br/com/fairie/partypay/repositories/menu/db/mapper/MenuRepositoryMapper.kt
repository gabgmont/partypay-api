package br.com.fairie.partypay.repositories.menu.db.mapper

import br.com.fairie.partypay.entity.OrderEntity
import br.com.fairie.partypay.usecase.menu.vo.Order

fun Order.toEntity(): OrderEntity = OrderEntity(
        id = id(),
        name = name,
        description = description,
        value = value
)

fun OrderEntity.toOrder(): Order = Order(
        id = id,
        name = name,
        description = description,
        value = value
)
