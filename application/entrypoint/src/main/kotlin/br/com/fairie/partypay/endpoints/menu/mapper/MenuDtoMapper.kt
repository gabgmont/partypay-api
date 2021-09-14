package br.com.fairie.partypay.endpoints.menu.mapper

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDTO
import br.com.fairie.partypay.endpoints.menu.dto.MenuDTO
import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order

fun Menu.toDto(): MenuDTO {
    val dtoList = ArrayList<CategoryDTO>()

    categoryList.forEach { category ->
        dtoList.add(category.toDto())
    }

    return MenuDTO(
        name = name,
        cateogryList = dtoList
    )
}

fun Category.toDto(): CategoryDTO {
    val dtoList = ArrayList<OrderDTO>()

    orderList.forEach{ order ->
        dtoList.add(order.toDto())
    }

    return CategoryDTO(
        name = name,
        orderList = dtoList
    )
}

fun Order.toDto(): OrderDTO =
    OrderDTO(
        name = name,
        description = description,
        value = value
    )