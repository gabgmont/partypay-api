package br.com.fairie.partypay.menu

import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.repositories.menu.json.repository.MenuJsonRepositoryImpl
import br.com.fairie.partypay.repositories.menu.json.repository.MenuJsonRepositoryImpl.Companion.CATEGORY_NOT_FOUND
import br.com.fairie.partypay.repositories.menu.json.repository.MenuJsonRepositoryImpl.Companion.MENU_NOT_FOUND
import br.com.fairie.partypay.repositories.menu.json.repository.MenuJsonRepositoryImpl.Companion.ORDER_NOT_FOUND
import br.com.fairie.partypay.repositories.menu.json.repository.MenuJsonRepositoryImpl.Companion.RESTAURANT_NOT_FOUND
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class MenuRepositoryImplTest {

    private val repository = MenuJsonRepositoryImpl()

    @Test
    fun getMenuByNameTest() {
        val menu = repository.getMenuByName("outback")

        assertNotNull(menu)
        assertEquals("Outback", menu.name)
        assertNotNull(menu.categoryList)
    }

    @Test
    fun menuNotFoundTest() {

        assertThrows<NotFoundException> {
            try {
                repository.getMenuByName("nome invalido")
            } catch (exception: NotFoundException) {
                assertEquals(MENU_NOT_FOUND, exception.message)
                throw exception
            }
        }
    }

    @Test
    fun getCategoryByNameTest() {
        val category = repository.getCategoryByName("outback", "fondue outback")

        assertNotNull(category)
        assertEquals("Fondue Outback", category.name)
        assertNotNull(category.orderList)
    }

    @Test
    fun categoryRestaurantNotFoundTest() {

        assertThrows<NotFoundException> {
            try {
                repository.getCategoryByName("nome invalido", "fondue outback")
            } catch (exception: NotFoundException) {
                assertEquals(RESTAURANT_NOT_FOUND, exception.message)
                throw exception
            }
        }
    }

    @Test
    fun categoryNotFoundTest() {

        assertThrows<NotFoundException> {
            try {
                repository.getCategoryByName("outback", "nome invalido")
            } catch (exception: NotFoundException) {
                assertEquals(CATEGORY_NOT_FOUND, exception.message)
                throw exception
            }
        }
    }

    @Test
    fun getOrderByNameTest() {
        val order = repository.getOrderByName("outback", "combo fondue")

        assertNotNull(order)
        assertEquals("COMBO FONDUE", order.name)
    }

    @Test
    fun orderRestaurantNotFoundTest() {

        assertThrows<NotFoundException> {
            try {
                repository.getOrderByName("nome invalido", "COMBO FONDUE")
            } catch (exception: NotFoundException) {
                assertEquals(RESTAURANT_NOT_FOUND, exception.message)
                throw exception
            }
        }
    }

    @Test
    fun orderNotFoundTest() {

        assertThrows<NotFoundException> {
            try {
                repository.getOrderByName("outback", "nome invalido")
            } catch (exception: NotFoundException) {
                assertEquals(ORDER_NOT_FOUND, exception.message)
                throw exception
            }
        }
    }
}