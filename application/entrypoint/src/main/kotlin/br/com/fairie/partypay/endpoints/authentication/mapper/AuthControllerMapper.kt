package br.com.fairie.partypay.endpoints.authentication.mapper

import br.com.fairie.partypay.endpoints.authentication.dto.AuthenticationDTO
import br.com.fairie.partypay.endpoints.authentication.form.LoginForm
import br.com.fairie.partypay.usecase.authentication.model.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.model.LoginData

fun LoginForm.toLoginData(): LoginData = LoginData(username, secret)

fun GeneratedToken.toDto(): AuthenticationDTO = AuthenticationDTO(type = "Bearer", token = token, expiration = expiration)
