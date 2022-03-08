package br.com.fairie.partypay.endpoints.menu.controller

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDTO
import br.com.fairie.partypay.endpoints.menu.dto.MenuDTO
import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.menu.dto.RestaurantsDTO
import br.com.fairie.partypay.endpoints.menu.mapper.toDTO
import br.com.fairie.partypay.endpoints.menu.mapper.toRestaurantsDTO
import br.com.fairie.partypay.exception.ThreadExecutionException
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import br.com.fairie.partypay.usecase.menu.model.Category
import br.com.fairie.partypay.usecase.menu.model.Menu
import br.com.fairie.partypay.usecase.menu.model.Order
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
class MenuController(
    private val threadPool: ThreadPool,
    private val useCase: MenuUseCase
) {

    @GetMapping("/restaurants")
    @ApiOperation(value = GET_RESTAURANTS_OPERATION_VALUE, notes = GET_RESTAURANTS_OPERATION_NOTES)
    fun getAllRestaurants(): List<RestaurantsDTO>{
        val response = useCase.getRestaurants()
        return response.map { menu -> menu.toRestaurantsDTO() }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = GET_MENU_OPERATION_VALUE, notes = GET_MENU_OPERATION_NOTES)
    fun browseMenu(@PathVariable id: Long): MenuDTO {
        var response: Menu? = null

        threadPool.executor.submit { response = useCase.getMenu(id)
            Thread.sleep(10000)
        }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }
            future.get()
            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")
            return (response as Menu).toDTO()
        }
    }

    @GetMapping("/category/{categoryId}")
    @ApiOperation(value = GET_MENU_CATEGORY_OPERATION_VALUE, notes = GET_MENU_CATEGORY_OPERATION_NOTES)
    fun browseCategory(@PathVariable categoryId: Long): CategoryDTO {
        var response: Category? = null

        threadPool.executor.submit { response = useCase.getMenuCategory(categoryId) }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }
            future.get()
            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")
            return (response as Category).toDTO()
        }
    }

    @GetMapping("/order/{order}")
    @ApiOperation(value = GET_MENU_ORDER_OPERATION_VALUE, notes = GET_MENU_ORDER_OPERATION_NOTES)
    fun browseOrder(@PathVariable order: Long): OrderDTO {
        var response: Order? = null

        threadPool.executor.submit { response = useCase.getMenuOrder(order) }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }
            future.get()
            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")
            return (response as Order).toDTO()
        }
    }
}
