package br.com.fairie.partypay.usecase.user.vo

import br.com.fairie.partypay.vo.CPF
import br.com.fairie.partypay.vo.Email
import br.com.fairie.partypay.vo.Phone
import br.com.fairie.partypay.vo.Photo

class User(
    val id: Long,
    val name: String,
    val cpf: CPF,
    val email: Email,
    val secret: String,
    val phone: Phone,
    val photo: Photo?,
    val profiles: MutableCollection<Profile>
){

}