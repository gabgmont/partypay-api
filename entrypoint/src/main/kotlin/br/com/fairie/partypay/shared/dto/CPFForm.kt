package br.com.fairie.partypay.shared.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class CPFForm(
    @NotNull @NotBlank @Size(min = 11, max = 11)
    val cpf: String?
) {
    override fun toString(): String {
        return "[cpf='$cpf']"
    }
}