package br.com.fairie.partypay.usecase.authentication.vo

import br.com.fairie.partypay.vo.CPF

class LoginData(
        val cpf: CPF,
        val secret: String
)