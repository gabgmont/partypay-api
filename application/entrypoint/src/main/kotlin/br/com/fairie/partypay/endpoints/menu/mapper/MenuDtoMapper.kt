package br.com.fairie.partypay.endpoints.menu.mapper

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDTO
import br.com.fairie.partypay.endpoints.menu.dto.MenuDTO
import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order

fun Menu.toDTO(): MenuDTO {
    val dtoList = ArrayList<CategoryDTO>()

    categoryList.forEach { category ->
        dtoList.add(category.toDTO())
    }

    return MenuDTO(
        name = name,
        cateogryList = dtoList
    )
}

fun Category.toDTO(): CategoryDTO {
    val dtoList = ArrayList<OrderDTO>()

    orderList.forEach{ order ->
        dtoList.add(order.toDTO())
    }

    return CategoryDTO(
        name = name,
        orderList = dtoList
    )
}

fun Order.toDTO(): OrderDTO =
    OrderDTO(
        name = name,
        description = description,
        value = value
    )