package br.com.fairie.partypay.endpoints.authentication.mapper

import br.com.fairie.partypay.endpoints.authentication.dto.AuthenticationDTO
import br.com.fairie.partypay.endpoints.authentication.form.LoginForm
import br.com.fairie.partypay.usecase.authentication.vo.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.vo.LoginData
import br.com.fairie.partypay.vo.CPF

fun LoginForm.toLoginData(): LoginData = LoginData(CPF(cpf), secret)

fun GeneratedToken.toDto(): AuthenticationDTO = AuthenticationDTO(type = "Bearer", token = token, expiration = expiration)