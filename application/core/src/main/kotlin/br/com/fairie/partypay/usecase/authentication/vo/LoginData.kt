package br.com.fairie.partypay.usecase.authentication.vo

import br.com.fairie.partypay.vo.Email

class LoginData(
    val email: Email,
    val secret: String
)