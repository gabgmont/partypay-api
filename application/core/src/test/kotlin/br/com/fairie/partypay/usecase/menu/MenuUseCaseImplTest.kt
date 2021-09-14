package br.com.fairie.partypay.usecase.menu

import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order
import br.com.fairie.partypay.usecase.menu.impl.MenuUseCaseImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MenuUseCaseImplTest {

    private val order = Order("Bloomin' Onions", "description", 99.99)
    private val category = Category("Aperitivos", arrayListOf(order))
    private val menu = Menu(1, "Outback", arrayListOf(category))

    private val repository = mockk<MenuRepository>().also { repository ->
        every { repository.getOrderByName("outback", "bloomin' onions") } returns order
        every { repository.getCategoryByName("outback", "aperitivos") } returns category
        every { repository.getMenuByName("outback") } returns menu
    }

    private val useCase = MenuUseCaseImpl(repository)

    @Test
    fun searchMenuTest() {
        val menu = useCase.getMenu("outback")

        assertEquals(menu.name, "Outback")
    }

    @Test
    fun searchCategoryTest() {
        val category = useCase.getMenuCategory("outback", "aperitivos")

        assertEquals(category.name, "Aperitivos")
    }

    @Test
    fun searchOrderTest() {
        val order = useCase.getMenuOrder("outback", "bloomin' onions")

        assertEquals(order.name, "Bloomin' Onions")
    }
}