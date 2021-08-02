package br.com.fairie.partypay.endpoints.menu.mapper

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDto
import br.com.fairie.partypay.endpoints.menu.dto.MenuDto
import br.com.fairie.partypay.endpoints.menu.dto.OrderDto
import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order

fun Menu.toDto(): MenuDto {
    val dtoList = ArrayList<CategoryDto>()

    categoryList.forEach { category ->
        dtoList.add(category.toDto())
    }

    return MenuDto(
        name = name,
        cateogryList = dtoList
    )
}

fun Category.toDto(): CategoryDto {
    val dtoList = ArrayList<OrderDto>()

    orderList.forEach{ order ->
        dtoList.add(order.toDto())
    }

    return CategoryDto(
        name = name,
        orderList = dtoList
    )
}

fun Order.toDto(): OrderDto =
    OrderDto(
        name = name,
        description = description,
        value = value
    )