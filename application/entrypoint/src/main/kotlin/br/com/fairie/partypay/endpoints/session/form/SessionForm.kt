package br.com.fairie.partypay.endpoints.session.form

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.usecase.menu.vo.Menu

class SessionForm(
    val id: Long,
    val restaurant: String,
    val table: Int,
    val menu: Menu
)