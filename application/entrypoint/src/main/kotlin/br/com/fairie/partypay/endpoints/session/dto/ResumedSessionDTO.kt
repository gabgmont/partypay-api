package br.com.fairie.partypay.endpoints.session.dto

import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO

class ResumedSessionDTO(
        val id: Long,
        val restaurant: String,
        val table: Int,
        val userList: List<ResumedUserDTO>,
        val orderList: List<ResumedSessionOrderDTO>
)