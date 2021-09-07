package br.com.fairie.partypay.endpoints.authentication.mapper

import br.com.fairie.partypay.endpoints.authentication.dto.TokenDto
import br.com.fairie.partypay.endpoints.authentication.form.LoginForm
import br.com.fairie.partypay.usecase.authentication.vo.GeneratedToken
import br.com.fairie.partypay.usecase.authentication.vo.LoginData
import br.com.fairie.partypay.vo.Email

fun LoginForm.toLoginData(): LoginData = LoginData(Email(email), secret)

fun GeneratedToken.toDto(): TokenDto = TokenDto(token, expiration)