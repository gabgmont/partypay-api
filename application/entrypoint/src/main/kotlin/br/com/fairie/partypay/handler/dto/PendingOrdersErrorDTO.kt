package br.com.fairie.partypay.handler.dto

import br.com.fairie.partypay.endpoints.session.dto.ResumedSessionOrderDTO
import com.fasterxml.jackson.annotation.JsonProperty

class PendingOrdersErrorDTO(
        @JsonProperty(value = "code")
        val code: Int,
        @JsonProperty(value = "message")
        val message: String,
        @JsonProperty(value = "order_list")
        val orders: List<ResumedSessionOrderDTO>
)