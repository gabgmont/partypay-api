package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import java.math.BigDecimal

class SessionUserDTO(
    val user: UserDTO,
    val orders: MutableList<OrderDTO>,
    var totalValue: BigDecimal
)