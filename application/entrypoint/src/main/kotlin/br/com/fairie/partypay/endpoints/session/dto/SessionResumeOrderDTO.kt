package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.ResumedOrderDTO
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class SessionResumeOrderDTO(

    @JsonProperty("order")
    val resumeOrder: ResumedOrderDTO,

    @JsonProperty("value_per_user")
    val valuePerUser: BigDecimal
)
