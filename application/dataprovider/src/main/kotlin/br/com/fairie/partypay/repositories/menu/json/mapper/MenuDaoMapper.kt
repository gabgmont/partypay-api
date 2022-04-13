package br.com.fairie.partypay.repositories.menu.json.mapper

import br.com.fairie.partypay.repositories.menu.json.dao.CategoryDao
import br.com.fairie.partypay.repositories.menu.json.dao.MenuDao
import br.com.fairie.partypay.repositories.menu.json.dao.OrderDao
import br.com.fairie.partypay.usecase.menu.model.Category
import br.com.fairie.partypay.usecase.menu.model.Menu
import br.com.fairie.partypay.usecase.menu.model.Order
import java.math.BigDecimal

fun MenuDao.toVo(): Menu = Menu(
        id = id,
        name = restaurant,
        image = image,
        categoryList = menu.map { menu -> menu.toVo() }
)

fun CategoryDao.toVo(): Category = Category(
        id = 0,
        name = name,
        orderList = orders.map { order -> order.toVo() }
)

fun OrderDao.toVo(): Order = Order(
        id = 0,
        name = name,
        image = image,
        description = description,
        value = BigDecimal(value)
)
