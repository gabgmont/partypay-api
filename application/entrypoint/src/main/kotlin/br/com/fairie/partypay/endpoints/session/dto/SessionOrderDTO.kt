package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import java.math.BigDecimal

class SessionOrderDTO(
    val order: OrderDTO,
    val valuePerUser: BigDecimal,
    val users: List<UserDTO>
)