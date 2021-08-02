package br.com.fairie.partypay.menu.repository

import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.menu.dao.MenuDao
import br.com.fairie.partypay.menu.mapper.toVo
import br.com.fairie.partypay.usecase.menu.MenuRepository
import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.util.ResourceUtils
import java.nio.file.Files

class MenuRepositoryImpl : MenuRepository {

    override fun getMenuByName(restaurant: String): Menu {
        val dao = getMenusFromResource()
        if (dao.restaurant.trim().lowercase() == restaurant.trim().lowercase())
            return dao.toVo()
        else throw NotFoundException("Menu not found.")
    }

    override fun getCategoryByName(restaurant: String, category: String): Category {
        val menuDao = getMenusFromResource()
        if (menuDao.restaurant.trim().lowercase() == restaurant.trim().lowercase()) {
            menuDao.menu.forEach { categoryDao ->
                if (categoryDao.name.trim().lowercase() == category.trim().lowercase()) {
                    return categoryDao.toVo()
                }
            }
            throw NotFoundException("Category not found.")
        } else throw NotFoundException("Restaurant not found.")
    }

    override fun getOrderByName(restaurant: String, order: String): Order {
        val menuDao = getMenusFromResource()
        if (menuDao.restaurant.trim().lowercase() == restaurant.trim().lowercase()) {
            menuDao.menu.forEach { categoryDao ->
                categoryDao.orders.forEach { orderDao ->
                    if (orderDao.name.trim().lowercase() == order.trim().lowercase())
                        return orderDao.toVo()
                }
            }
            throw NotFoundException("Category not found.")
        } else throw NotFoundException("Restaurant not found.")
    }
}

private fun getMenusFromResource(): MenuDao {
    val content =
        String(Files.readAllBytes(ResourceUtils.getFile("dataprovider/src/main/resources/menu.json").toPath()))
    return jacksonObjectMapper().readValue(content, MenuDao::class.java)
}
