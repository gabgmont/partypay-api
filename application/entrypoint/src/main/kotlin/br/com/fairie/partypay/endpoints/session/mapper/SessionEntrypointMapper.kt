package br.com.fairie.partypay.endpoints.session.mapper

import br.com.fairie.partypay.endpoints.menu.mapper.toDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionOrderDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionResumeDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionUserDTO
import br.com.fairie.partypay.endpoints.session.form.CPFListForm
import br.com.fairie.partypay.endpoints.session.form.SessionForm
import br.com.fairie.partypay.endpoints.user.mapper.toDTO
import br.com.fairie.partypay.usecase.session.mapper.valuePerUser
import br.com.fairie.partypay.usecase.session.vo.*
import br.com.fairie.partypay.vo.CPF

fun CPFListForm.toCPFList(): List<CPF> = cpfList.map { cpf -> CPF(cpf) }


fun SessionForm.toVo(status: SessionStatus): Session = Session(
        id = 0,
        restaurant = restaurant,
        table = table,
        status = status,
        users = arrayListOf(),
        orders = arrayListOf()
)


fun Session.toDTO(): SessionDTO = SessionDTO(
        id = id(),
        restaurant = restaurant,
        table = table,
        userList = users.map { user -> user.toDTO() },
        orderList = orders.map { sessionOrder -> sessionOrder.toDTO() }
)


fun SessionOrder.toDTO(): SessionOrderDTO = SessionOrderDTO(
        order = order.toDTO(),
        users = users.map { user -> user.toDTO() },
        valuePerUser = valuePerUser()
)

fun SessionResume.toDTO(): SessionResumeDTO = SessionResumeDTO(
        userList = users.map { user -> user.toDTO() },
        check = check.toDouble()
)


fun SessionUser.toDTO(): SessionUserDTO = SessionUserDTO(
        user = user.toDTO(),
        orders = orders.map { order -> order.toDTO() }.toMutableList(),
        totalValue = totalValue
)

























