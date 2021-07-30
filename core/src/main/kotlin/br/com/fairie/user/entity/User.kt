package br.com.fairie.user.entity

import br.com.fairie.shared.vo.CPF
import br.com.fairie.shared.vo.Email
import br.com.fairie.shared.vo.Phone
import br.com.fairie.shared.vo.Photo

class User(
    val nome: String,
    val cpf: CPF,
    val email: Email,
    val phone: Phone,
    val photo: Photo?
) {
}