package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.usecase.session.vo.SessionOrderStatus
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class SessionOrderDTO(
        @JsonProperty(value = "order")
        val order: OrderDTO,
        @JsonProperty(value = "order_status")
        val status: SessionOrderStatus,
        @JsonProperty(value = "value_per_user")
        val valuePerUser: BigDecimal,
        @JsonProperty(value = "user_list")
        val users: List<UserDTO>
)