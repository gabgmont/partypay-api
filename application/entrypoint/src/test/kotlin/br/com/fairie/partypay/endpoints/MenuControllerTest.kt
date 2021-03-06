package br.com.fairie.partypay.endpoints

import br.com.fairie.partypay.endpoints.menu.controller.MenuController
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.net.URI

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
class MenuControllerTest {

    private val mvc = MockMvcBuilders.standaloneSetup(MenuController::class).build()

    @Test
    fun testNotFound() {

        val content = "madero"
        val uri = URI("/menu/$content")

        mvc.perform(MockMvcRequestBuilders.get(uri))
            .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}