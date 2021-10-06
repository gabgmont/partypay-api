package br.com.fairie.partypay.repositories.menu.json.repository

import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.repositories.menu.json.OutbackMenu
import br.com.fairie.partypay.repositories.menu.json.dao.MenuDao
import br.com.fairie.partypay.repositories.menu.json.mapper.toVo
import br.com.fairie.partypay.usecase.menu.MenuJsonRepository
import br.com.fairie.partypay.usecase.menu.vo.Category
import br.com.fairie.partypay.usecase.menu.vo.Menu
import br.com.fairie.partypay.usecase.menu.vo.Order
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files

class MenuJsonRepositoryImpl : MenuJsonRepository {

    companion object{
        const val MENU_NOT_FOUND = "Menu not found."
        const val RESTAURANT_NOT_FOUND = "Restaurant not found."
        const val CATEGORY_NOT_FOUND = "Category not found."
        const val ORDER_NOT_FOUND = "Order not found."
    }

    override fun getMenuByName(restaurant: String): Menu {
        val dao = getMenusFromResource()

        if (dao.restaurant.trim().lowercase() == restaurant.trim().lowercase())
            return dao.toVo()
        else throw NotFoundException(MENU_NOT_FOUND)
    }

    override fun getCategoryByName(restaurant: String, category: String): Category {
        val menuDao = getMenusFromResource()

        if (menuDao.restaurant.trim().lowercase() == restaurant.trim().lowercase()) {
            menuDao.menu.forEach { categoryDao ->
                if (categoryDao.name.trim().lowercase() == category.trim().lowercase()) {
                    return categoryDao.toVo()
                }
            }
            throw NotFoundException(CATEGORY_NOT_FOUND)
        } else throw NotFoundException(RESTAURANT_NOT_FOUND)
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
            throw NotFoundException(ORDER_NOT_FOUND)
        } else throw NotFoundException(RESTAURANT_NOT_FOUND)
    }

    private fun getMenusFromResource(): MenuDao {
//        val url = javaClass.classLoader.getResource("menu.json") ?: throw FileNotFoundException("File Not Found.")
//        val file = File(url.file).toPath()
//
//        val content = String(Files.readAllBytes(file))
        return jacksonObjectMapper().readValue(OutbackMenu.menuJson, MenuDao::class.java)
    }
}




