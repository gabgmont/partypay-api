package br.com.fairie.partypay.endpoints.menu.controller

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDto
import br.com.fairie.partypay.endpoints.menu.dto.MenuDto
import br.com.fairie.partypay.endpoints.menu.dto.OrderDto
import br.com.fairie.partypay.endpoints.menu.mapper.toDto
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
@Api(
    description = "Menu Related Operations.",
    tags = ["Menu"]
)
class MenuController(
    val useCase: MenuUseCase
) {

    @GetMapping
    @ApiOperation(value = "Browse restaurants Menu")
    fun browseMenu(restaurant: String): MenuDto {
        val response = useCase.getMenu(restaurant)
        return response.toDto()
    }

    @GetMapping("/category")
    @ApiOperation(value = "Browse restaurant menu cateogry")
    fun browseCategory(restaurant: String, category: String): CategoryDto{
        val response = useCase.getMenuCategory(restaurant, category)
        return response.toDto()
    }

    @GetMapping("/order")
    @ApiOperation(value = "Browse restaurant menu order")
    fun browseOrder(restaurant: String, order: String): OrderDto{
        val response = useCase.getMenuOrder(restaurant, order)
        return response.toDto()
    }

}