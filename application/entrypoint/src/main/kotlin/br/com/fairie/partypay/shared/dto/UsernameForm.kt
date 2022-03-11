package br.com.fairie.partypay.shared.dto

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.annotations.ApiModelProperty

class UsernameForm(
    @ApiModelProperty(value = "Username / Email", example = "example / example@email.com")
    val username: String?
) {
    override fun toString(): String {
        return ObjectMapper().writeValueAsString(this)
    }
}
