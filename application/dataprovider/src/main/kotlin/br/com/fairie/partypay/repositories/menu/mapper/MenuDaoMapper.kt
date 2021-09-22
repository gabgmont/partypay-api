package br.com.fairie.partypay.repositories.menu.mapper

import br.com.fairie.partypay.repositories.menu.dao.CategoryDao
import br.com.fairie.partypay.repositories.menu.dao.MenuDao
import br.com.fairie.partypay.repositories.menu.dao.OrderDao
import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order
import java.math.BigDecimal

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
        value = BigDecimal(value)
    )