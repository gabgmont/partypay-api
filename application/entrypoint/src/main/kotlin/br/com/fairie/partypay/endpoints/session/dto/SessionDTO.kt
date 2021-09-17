package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.usecase.menu.vo.Menu

class SessionDTO(
    val id: Long,
    val restaurant: String,
    val table: Int,
    val menu: Menu,
    val userList: List<UserDTO>,
    val orderList: List<OrderDTO>
)