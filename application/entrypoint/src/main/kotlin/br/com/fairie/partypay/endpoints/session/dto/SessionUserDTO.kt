package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.user.dto.UserDTO

class SessionUserDTO(
    val user: UserDTO,
    val orders: List<OrderDTO>,
    val personalTotal: Double
)