package br.com.fairie.partypay.endpoints.menu.controller

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDTO
import br.com.fairie.partypay.endpoints.menu.dto.MenuDTO
import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.menu.mapper.toDto
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import br.com.fairie.partypay.utils.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menu")
@Api(tags = [MENU_TAG_TITLE], description = MENU_TAG_DESCRIPTION)
class MenuController(val useCase: MenuUseCase) {

    @GetMapping("/{restaurant}")
    @ApiOperation(value = GET_MENU_OPERATION_VALUE, notes = GET_MENU_OPERATION_NOTES)
    fun browseMenu(@PathVariable restaurant: String): MenuDTO {
        val response = useCase.getMenu(restaurant)
        return response.toDto()
    }

    @GetMapping("/{restaurant}/category/{category}")
    @ApiOperation(value = GET_MENU_CATEGORY_OPERATION_VALUE, notes = GET_MENU_CATEGORY_OPERATION_NOTES)
    fun browseCategory(@PathVariable restaurant: String, @PathVariable category: String): CategoryDTO{
        val response = useCase.getMenuCategory(restaurant, category)
        return response.toDto()
    }

    @GetMapping("/{restaurant}/order/{order}")
    @ApiOperation(value = GET_MENU_ORDER_OPERATION_VALUE, notes = GET_MENU_ORDER_OPERATION_NOTES)
    fun browseOrder(@PathVariable restaurant: String, @PathVariable order: String): OrderDTO{
        val response = useCase.getMenuOrder(restaurant, order)
        return response.toDto()
    }
}