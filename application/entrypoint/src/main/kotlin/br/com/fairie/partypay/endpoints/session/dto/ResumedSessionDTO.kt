package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO
import com.fasterxml.jackson.annotation.JsonProperty

class ResumedSessionDTO(
        @JsonProperty(value = "id")
        val id: Long,
        @JsonProperty(value = "restaurant")
        val restaurant: String,
        @JsonProperty(value = "table")
        val table: Int,
        @JsonProperty(value = "user_list")
        val users: List<ResumedUserDTO>,
        @JsonProperty(value = "order_list")
        val orders: List<ResumedSessionOrderDTO>
)