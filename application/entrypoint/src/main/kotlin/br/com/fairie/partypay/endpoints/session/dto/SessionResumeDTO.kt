package br.com.fairie.partypay.endpoints.session.dto

import com.fasterxml.jackson.annotation.JsonProperty

class SessionResumeDTO(
        @JsonProperty(value = "user_list")
        val users: List<ResumedSessionUserDTO>,
        @JsonProperty(value = "check")
        val check: Double
)