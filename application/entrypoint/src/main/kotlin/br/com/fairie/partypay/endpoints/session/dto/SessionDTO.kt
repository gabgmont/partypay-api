package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.user.dto.UserDTO

class SessionDTO(
    val id: Long?,
    val restaurant: String,
    val table: Int,
    val userList: List<UserDTO>,
    val orderList: List<SessionOrderDTO>
)