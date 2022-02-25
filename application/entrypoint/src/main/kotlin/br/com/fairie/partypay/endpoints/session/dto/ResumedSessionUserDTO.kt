package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class ResumedSessionUserDTO(
        @JsonProperty(value = "user")
        val user: ResumedUserDTO,
        @JsonProperty(value = "order_list")
        val orders: MutableList<SessionResumeOrderDTO>,
        @JsonProperty(value = "total_value")
        var totalValue: BigDecimal
)
