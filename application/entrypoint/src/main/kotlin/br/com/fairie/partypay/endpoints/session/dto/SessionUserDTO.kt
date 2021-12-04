package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class SessionUserDTO(
        @JsonProperty(value = "user")
        val user: UserDTO,
        @JsonProperty(value = "order_list")
        val orders: MutableList<OrderDTO>,
        @JsonProperty(value = "total_value")
        var totalValue: BigDecimal
)