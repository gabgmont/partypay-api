package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.ResumedOrderDTO
import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO
import java.math.BigDecimal

class ResumedSessionOrderDTO(
        val order: ResumedOrderDTO,
        val valuePerUser: BigDecimal,
        val users: List<ResumedUserDTO>
)