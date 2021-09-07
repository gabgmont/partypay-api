package br.com.fairie.partypay.endpoints.user.controller

import br.com.fairie.partypay.shared.dto.CPFDto
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.endpoints.user.dto.UserDto
import br.com.fairie.partypay.endpoints.user.mapper.toCPForNull
import br.com.fairie.partypay.endpoints.user.mapper.toDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/user")
@Api(
    description = "User Operations",
    tags = ["User"]
)
class UserController(
    private val useCase: UserUseCase
) {

    @GetMapping
    @ApiOperation(
        value = "Retrieve user information with CPF.",
        notes = """Operation used to get user from Database. 
            |
        """
    )

    fun getUser(@Valid cpfDto: CPFDto): ResponseEntity<List<UserDto>> {

        val request = cpfDto.toCPForNull()
        val response = useCase.get(request).toDto()

        return ResponseEntity.ok(response)
    }
}