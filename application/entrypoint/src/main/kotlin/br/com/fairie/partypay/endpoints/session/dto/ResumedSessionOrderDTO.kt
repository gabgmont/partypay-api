package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.ResumedOrderDTO
import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO
import br.com.fairie.partypay.usecase.session.model.SessionOrderStatus
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class ResumedSessionOrderDTO(
        @JsonProperty(value = "order")
        val order: ResumedOrderDTO,
        @JsonProperty(value = "order_status")
        val status: SessionOrderStatus,
        @JsonProperty(value = "value_per_user")
        val valuePerUser: BigDecimal,
        @JsonProperty(value = "user_list")
        val users: List<ResumedUserDTO>
)
