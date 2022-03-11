package br.com.fairie.partypay.endpoints.user.controller

import br.com.fairie.partypay.endpoints.user.dto.SocialUserForm
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.endpoints.user.dto.UserForm
import br.com.fairie.partypay.endpoints.user.mapper.toDTO
import br.com.fairie.partypay.endpoints.user.mapper.toDto
import br.com.fairie.partypay.endpoints.user.mapper.toModel
import br.com.fairie.partypay.exception.ThreadExecutionException
import br.com.fairie.partypay.shared.dto.UsernameForm
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.utils.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
@Api(tags = [USER_TAG_TITLE], description = USER_TAG_DESCRIPTION)
class UserController(
        private val threadPool: ThreadPool,
        private val useCase: UserUseCase
) {

    @GetMapping
    @ApiOperation(value = GET_USER_OPERATION_VALUE, notes = GET_USER_OPERATION_NOTES)
    fun getUser(usernameForm: UsernameForm): ResponseEntity<List<UserDTO>> {
        var response: List<UserDTO>? = null

        threadPool.executor.submit {
            response = useCase.get(usernameForm.username).toDto()

        }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }

            future.get()
            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")

            return ResponseEntity.ok(response)
        }
    }

    @PostMapping("/register")
    @ApiOperation(value = REGISTER_USER_OPERATION_VALUE, notes = REGISTER_USER_OPERATION_NOTES)
    fun registerUser(@RequestBody form: UserForm): ResponseEntity<UserDTO> {
        var response: UserDTO? = null

        threadPool.executor.submit {
            val request = form.toModel()
            response = useCase.register(request).toDTO()

        }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }
            future.get()

            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")

            return ResponseEntity.ok(response)
        }
    }

    @PostMapping("/register/social")
    @ApiOperation(value = SOCIAL_REGISTER_USER_OPERATION_VALUE, notes = SOCIAL_REGISTER_USER_OPERATION_NOTES)
    fun registerSocialUser(@RequestBody form: SocialUserForm): ResponseEntity<UserDTO> {
        var response: UserDTO? = null

        threadPool.executor.submit {
            val request = form.toModel()
            response = useCase.socialRegister(request).toDTO()

        }.also { future ->
            while (!future.isDone) {
                Thread.sleep(100)
            }
            future.get()

            if (response == null) throw ThreadExecutionException("Failed to execute Thread.")

            return ResponseEntity.ok(response)
        }
    }
}
