package br.com.fairie.shared.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class CPFDto(
    @NotNull @NotBlank @Size(min = 11, max = 11)
    val cpf: String?
) {
    override fun toString(): String {
        return "[cpf='$cpf']"
    }
}