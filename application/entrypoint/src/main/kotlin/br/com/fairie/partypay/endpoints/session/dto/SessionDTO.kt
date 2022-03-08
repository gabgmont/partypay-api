package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.usecase.session.vo.SessionStatus
import com.fasterxml.jackson.annotation.JsonProperty

class SessionDTO(
        @JsonProperty(value = "id")
        val id: Long,
        @JsonProperty(value = "restaurant")
        val restaurant: String,
        @JsonProperty(value = "table")
        val table: Int,
        @JsonProperty(value = "session_status")
        val status: SessionStatus,
        @JsonProperty(value = "user_list")
        val users: List<UserDTO>,
        @JsonProperty(value = "order_list")
        val orders: List<SessionOrderDTO>
)
