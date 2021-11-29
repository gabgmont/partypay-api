package br.com.fairie.partypay.repositories.menu.json.mapper

import br.com.fairie.partypay.repositories.menu.json.dao.CategoryDao
import br.com.fairie.partypay.repositories.menu.json.dao.MenuDao
import br.com.fairie.partypay.repositories.menu.json.dao.OrderDao
import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order
import java.math.BigDecimal

fun MenuDao.toVo(): Menu = Menu(
        id = id,
        name = restaurant,
        categoryList = menu.map { menu -> menu.toVo() }
)

fun CategoryDao.toVo(): Category = Category(
        name = name,
        orderList = orders.map { order -> order.toVo() }
)

fun OrderDao.toVo(): Order = Order(
        id = 0,
        name = name,
        description = description,
        value = BigDecimal(value)
)