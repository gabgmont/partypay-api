package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.usecase.session.model.SessionStatus
import com.fasterxml.jackson.annotation.JsonProperty

class SessionResumeDTO(
        @JsonProperty(value = "user_list")
        val users: List<ResumedSessionUserDTO>,
        @JsonProperty(value = "session_status")
        val status: SessionStatus,
        @JsonProperty(value = "check")
        val check: Double
)
