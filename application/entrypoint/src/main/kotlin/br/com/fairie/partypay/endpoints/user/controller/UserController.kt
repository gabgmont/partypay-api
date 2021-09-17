package br.com.fairie.partypay.endpoints.user.controller

import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.endpoints.user.mapper.toCPForNull
import br.com.fairie.partypay.endpoints.user.mapper.toDto
import br.com.fairie.partypay.exception.ThreadExecutionException
import br.com.fairie.partypay.shared.dto.CPFForm
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.utils.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Api(tags = [USER_TAG_TITLE], description = USER_TAG_DESCRIPTION)
class UserController(
    private val threadPool: ThreadPool,
    private val useCase: UserUseCase
    ) {

    @GetMapping
    @ApiOperation(value = GET_USER_OPERATION_VALUE, notes = GET_USER_OPERATION_NOTES)

    fun getUser(cpfForm: CPFForm): ResponseEntity<List<UserDTO>> {
        var response: List<UserDTO>? = null

        threadPool.executor.submit {
            val request = cpfForm.toCPForNull()
            response = useCase.get(request).toDto()

        }.also { future ->
            while (!future.isDone) { Thread.sleep(100) }

            future.get()
            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")

            return ResponseEntity.ok(response)
        }
    }
}