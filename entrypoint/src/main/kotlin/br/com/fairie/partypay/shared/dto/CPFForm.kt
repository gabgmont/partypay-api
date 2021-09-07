package br.com.fairie.partypay.shared.dto

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class CPFForm(
    @ApiModelProperty(value = "CPF", example = "000.000.000-00 / 00000000000")
    val cpf: String?
) {
    override fun toString(): String {
        return "[cpf='$cpf']"
    }
}