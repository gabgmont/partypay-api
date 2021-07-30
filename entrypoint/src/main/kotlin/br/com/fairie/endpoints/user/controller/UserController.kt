package br.com.fairie.endpoints.user.controller

import br.com.fairie.shared.dto.CPFDto
import br.com.fairie.user.UserUseCase
import br.com.fairie.endpoints.user.dto.UserDto
import br.com.fairie.endpoints.user.mapper.toCPF
import br.com.fairie.endpoints.user.mapper.toDto
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
    val useCase: UserUseCase
) {

    @GetMapping
    @ApiOperation(
        value = "Retrieve user information with CPF.",
        notes = """Operation used to get user from Firebase"""
    )

    fun getUser(@Valid cpfDto: CPFDto): ResponseEntity<UserDto> {

        val request = cpfDto.toCPF()
        val response = useCase.get(request).toDto()

        return ResponseEntity.ok(response)
    }
}