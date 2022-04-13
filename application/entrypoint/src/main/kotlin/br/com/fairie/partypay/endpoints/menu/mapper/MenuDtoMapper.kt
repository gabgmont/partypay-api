package br.com.fairie.partypay.endpoints.menu.mapper

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDTO
import br.com.fairie.partypay.endpoints.menu.dto.MenuDTO
import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.menu.dto.RestaurantsDTO
import br.com.fairie.partypay.usecase.menu.model.Category
import br.com.fairie.partypay.usecase.menu.model.Menu
import br.com.fairie.partypay.usecase.menu.model.Order

fun Menu.toDTO(): MenuDTO = MenuDTO(
        id = id,
        name = name,
        image = image,
        categories = categoryList.map { category -> category.toDTO() }
)


fun Category.toDTO(): CategoryDTO = CategoryDTO(
        id = id,
        name = name,
        orders = orderList.map { order -> order.toDTO() }
)


fun Order.toDTO(): OrderDTO =
        OrderDTO(
                id = id,
                name = name,
                image = image,
                description = description,
                value = value
        )

fun Menu.toRestaurantsDTO(): RestaurantsDTO =
        RestaurantsDTO(
                id = id,
                name = name,
                image = image
        )
