package br.com.fairie.partypay.endpoints.authentication.controller

import br.com.fairie.partypay.endpoints.authentication.dto.TokenDto
import br.com.fairie.partypay.endpoints.authentication.form.LoginForm
import br.com.fairie.partypay.endpoints.authentication.mapper.toDto
import br.com.fairie.partypay.endpoints.authentication.mapper.toLoginData
import br.com.fairie.partypay.usecase.authentication.AuthenticationUseCase
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    val authUseCase: AuthenticationUseCase
) {

    @PostMapping
    @ApiOperation(value = "")
    fun authenticate(@RequestBody loginForm: LoginForm): TokenDto {
        val loginData = loginForm.toLoginData()
        val authenticated = authUseCase.authenticate(loginData)

        return authenticated.toDto()
    }

}