package br.com.fairie.partypay.endpoints.menu.mapper

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDTO
import br.com.fairie.partypay.endpoints.menu.dto.MenuDTO
import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.menu.dto.RestaurantsDTO
import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order

fun Menu.toDTO(): MenuDTO = MenuDTO(
    name = name,
    categories = categoryList.map { category -> category.toDTO() }
)


fun Category.toDTO(): CategoryDTO = CategoryDTO(
    name = name,
    orders = orderList.map { order -> order.toDTO() }
)


fun Order.toDTO(): OrderDTO =
    OrderDTO(
        name = name,
        description = description,
        value = value
    )

fun Menu.toRestaurantsDTO(): RestaurantsDTO =
    RestaurantsDTO(
        id = id,
        name = name
    )
