package br.com.fairie.partypay.menu.mapper

import br.com.fairie.partypay.menu.dao.CategoryDao
import br.com.fairie.partypay.menu.dao.MenuDao
import br.com.fairie.partypay.menu.dao.OrderDao
import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order

fun MenuDao.toVo(): Menu {
    val obList = ArrayList<Category>()

    menu.forEach { category ->
        obList.add(category.toVo())
    }

    return Menu(
        id = id,
        name = restaurant,
        categoryList = obList
    )
}

fun CategoryDao.toVo(): Category {
    val obList = ArrayList<Order>()

    orders.forEach{ order ->
        obList.add(order.toVo())
    }

    return Category(
        name = name,
        orderList = obList
    )
}

fun OrderDao.toVo(): Order =
    Order(
        name = name,
        description = description,
        value = value
    )