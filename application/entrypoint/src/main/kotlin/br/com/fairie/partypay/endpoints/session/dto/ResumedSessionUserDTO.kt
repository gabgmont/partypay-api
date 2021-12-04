package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.menu.dto.ResumedOrderDTO
import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO
import java.math.BigDecimal

class ResumedSessionUserDTO(
        val user: ResumedUserDTO,
        val orders: MutableList<ResumedOrderDTO>,
        var totalValue: BigDecimal
)