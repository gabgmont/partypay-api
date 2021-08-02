package br.com.fairie.partypay.usecase.user.entity

import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo

class User(
    val nome: String,
    val cpf: CPF,
    val email: Email,
    val phone: Phone,
    val photo: Photo?
) {
}