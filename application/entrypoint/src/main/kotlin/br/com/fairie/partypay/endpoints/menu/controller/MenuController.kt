package br.com.fairie.partypay.endpoints.menu.controller

import br.com.fairie.partypay.endpoints.menu.dto.CategoryDTO
import br.com.fairie.partypay.endpoints.menu.dto.MenuDTO
import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.menu.mapper.toDto
import br.com.fairie.partypay.exception.ThreadExecutionException
import br.com.fairie.partypay.usecase.menu.MenuUseCase
import br.com.fairie.partypay.usecase.menu.entity.Category
import br.com.fairie.partypay.usecase.menu.entity.Menu
import br.com.fairie.partypay.usecase.menu.entity.Order
import br.com.fairie.partypay.utils.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable
import javax.validation.constraints.Future

@RestController
@RequestMapping("/menu/{restaurant}")
@Api(tags = [MENU_TAG_TITLE], description = MENU_TAG_DESCRIPTION)
class MenuController(
    private val threadPool: ThreadPool,
    private val useCase: MenuUseCase
) {

    @GetMapping
    @ApiOperation(value = GET_MENU_OPERATION_VALUE, notes = GET_MENU_OPERATION_NOTES)
    fun browseMenu(@PathVariable restaurant: String): MenuDTO {
        var response: Menu? = null

        threadPool.executor.submit { response = useCase.getMenu(restaurant) }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }
            future.get()
            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")
            return (response as Menu).toDto()
        }
    }

    @GetMapping("/category/{category}")
    @ApiOperation(value = GET_MENU_CATEGORY_OPERATION_VALUE, notes = GET_MENU_CATEGORY_OPERATION_NOTES)
    fun browseCategory(@PathVariable restaurant: String, @PathVariable category: String): CategoryDTO {
        var response: Category? = null

        threadPool.executor.submit { response = useCase.getMenuCategory(restaurant, category) }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }
            future.get()
            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")
            return (response as Category).toDto()
        }
    }

    @GetMapping("/order/{order}")
    @ApiOperation(value = GET_MENU_ORDER_OPERATION_VALUE, notes = GET_MENU_ORDER_OPERATION_NOTES)
    fun browseOrder(@PathVariable restaurant: String, @PathVariable order: String): OrderDTO {
        var response: Order? = null

        threadPool.executor.submit { response = useCase.getMenuOrder(restaurant, order) }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }
            future.get()
            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")
            return (response as Order).toDto()
        }
    }
}