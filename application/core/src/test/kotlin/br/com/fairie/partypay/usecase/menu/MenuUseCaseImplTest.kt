package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.model.Category
import br.com.fairie.partypay.usecase.menu.model.Menu
import br.com.fairie.partypay.usecase.menu.model.Order
import br.com.fairie.partypay.usecase.menu.impl.MenuUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MenuUseCaseImplTest {

    private val order = Order(0, "Bloomin' Onions", "image","description", BigDecimal(99.99))
    private val category = Category(1, "Aperitivos", arrayListOf(order))
    private val menu = Menu(1, "Outback", "image",arrayListOf(category))

    private val repository = mockk<MenuRepository>().also { repository ->
        every { repository.getOrderById(any()) } returns order
        every { repository.getCategoryById(any()) } returns category
        every { repository.getMenuById(any()) } returns menu
    }

    private val useCase = MenuUseCaseImpl(repository)

    @Test
    fun searchMenuTest() {
        val menu = useCase.getMenu(1)

        assertEquals(menu.name, "Outback")
    }

    @Test
    fun searchCategoryTest() {
        val category = useCase.getMenuCategory(1)

        assertEquals(category.name, "Aperitivos")
    }

    @Test
    fun searchOrderTest() {
        val order = useCase.getMenuOrder(1)

        assertEquals(order.name, "Bloomin' Onions")
    }
}
