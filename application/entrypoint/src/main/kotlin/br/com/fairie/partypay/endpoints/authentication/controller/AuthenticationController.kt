package br.com.fairie.partypay.endpoints.authentication.controller

import br.com.fairie.partypay.endpoints.authentication.dto.AuthenticationDTO
import br.com.fairie.partypay.endpoints.authentication.form.LoginForm
import br.com.fairie.partypay.endpoints.authentication.mapper.toDto
import br.com.fairie.partypay.endpoints.authentication.mapper.toLoginData
import br.com.fairie.partypay.usecase.authentication.AuthenticationUseCase
import br.com.fairie.partypay.utils.AUTH_TAG_DESCRIPTION
import br.com.fairie.partypay.utils.AUTH_TAG_TITLE
import br.com.fairie.partypay.utils.POST_AUTHENTICATION_NOTES
import br.com.fairie.partypay.utils.POST_AUTHENTICATION_VALUE
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@Api(
    tags = [AUTH_TAG_TITLE],
    description = AUTH_TAG_DESCRIPTION
)
class AuthenticationController(
    val authUseCase: AuthenticationUseCase
) {

    @PostMapping
    @ApiOperation(value = POST_AUTHENTICATION_VALUE, notes = POST_AUTHENTICATION_NOTES)
    fun authenticate(@RequestBody loginForm: LoginForm): AuthenticationDTO {
        val loginData = loginForm.toLoginData()
        val authenticated = authUseCase.authenticate(loginData)

        return authenticated.toDto()
    }

}