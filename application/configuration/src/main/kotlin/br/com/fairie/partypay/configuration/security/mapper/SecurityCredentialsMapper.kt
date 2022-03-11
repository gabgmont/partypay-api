package br.com.fairie.partypay.configuration.security.mapper

import br.com.fairie.partypay.usecase.authentication.model.LoginData
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

fun LoginData.toUsernamePasswordAuthToken(): UsernamePasswordAuthenticationToken =
    UsernamePasswordAuthenticationToken(username, secret)
