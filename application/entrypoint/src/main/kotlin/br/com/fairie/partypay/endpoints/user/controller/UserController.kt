package br.com.fairie.partypay.endpoints.user.controller

import br.com.fairie.partypay.shared.dto.CPFForm
import br.com.fairie.partypay.usecase.user.UserUseCase
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.endpoints.user.mapper.toCPForNull
import br.com.fairie.partypay.endpoints.user.mapper.toDto
import br.com.fairie.partypay.utils.GET_USER_OPERATION_NOTES
import br.com.fairie.partypay.utils.GET_USER_OPERATION_VALUE
import br.com.fairie.partypay.utils.USER_TAG_DESCRIPTION
import br.com.fairie.partypay.utils.USER_TAG_TITLE
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
@Api(tags = [USER_TAG_TITLE], description = USER_TAG_DESCRIPTION)
class UserController(private val useCase: UserUseCase) {

    @GetMapping
    @ApiOperation(value = GET_USER_OPERATION_VALUE, notes = GET_USER_OPERATION_NOTES)

    fun getUser(cpfForm: CPFForm): ResponseEntity<List<UserDTO>> {

        val request = cpfForm.toCPForNull()
        val response = useCase.get(request).toDto()

        return ResponseEntity.ok(response)
    }
}