package br.com.fairie.partypay.endpoints.menu.controller

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDto
import br.com.fairie.partypay.endpoints.menu.dto.MenuDto
import br.com.fairie.partypay.endpoints.menu.dto.OrderDto
import br.com.fairie.partypay.endpoints.menu.mapper.toDto
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import br.com.fairie.partypay.utils.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
@Api(tags = [MENU_TAG_TITLE], description = MENU_TAG_DESCRIPTION)
class MenuController(val useCase: MenuUseCase) {

    @GetMapping
    @ApiOperation(value = GET_MENU_OPERATION_VALUE, notes = GET_MENU_OPERATION_NOTES)
    fun browseMenu(restaurant: String): MenuDto {
        val response = useCase.getMenu(restaurant)
        return response.toDto()
    }

    @GetMapping("/category")
    @ApiOperation(value = GET_MENU_CATEGORY_OPERATION_VALUE, notes = GET_MENU_CATEGORY_OPERATION_NOTES)
    fun browseCategory(restaurant: String, category: String): CategoryDto{
        val response = useCase.getMenuCategory(restaurant, category)
        return response.toDto()
    }

    @GetMapping("/order")
    @ApiOperation(value = GET_MENU_ORDER_OPERATION_VALUE, notes = GET_MENU_ORDER_OPERATION_NOTES)
    fun browseOrder(restaurant: String, order: String): OrderDto{
        val response = useCase.getMenuOrder(restaurant, order)
        return response.toDto()
    }
}