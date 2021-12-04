package br.com.fairie.partypay.endpoints.session.mapper

import br.com.fairie.partypay.endpoints.menu.dto.ResumedOrderDTO
import br.com.fairie.partypay.endpoints.menu.mapper.toDTO
import br.com.fairie.partypay.endpoints.session.dto.*
import br.com.fairie.partypay.endpoints.session.form.CPFListForm
import br.com.fairie.partypay.endpoints.session.form.SessionForm
import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO
import br.com.fairie.partypay.endpoints.user.mapper.toDTO
import br.com.fairie.partypay.usecase.menu.vo.Order
import br.com.fairie.partypay.usecase.session.mapper.valuePerUser
import br.com.fairie.partypay.usecase.session.vo.*
import br.com.fairie.partypay.usecase.user.vo.User
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
        userList = users.map { user -> user.toResumedDTO() },
        check = check.toDouble()
)


fun SessionUser.toResumedDTO(): ResumedSessionUserDTO = ResumedSessionUserDTO(
        user = user.toResumedDTO(),
        orders = orders.map { order -> order.toResumedDTO() }.toMutableList(),
        totalValue = totalValue
)

fun Session.toResumedDTO(): ResumedSessionDTO =
        ResumedSessionDTO(
                id = id(),
                restaurant = restaurant,
                table = table,
                userList = users.map { user -> user.toResumedDTO() },
                orderList = orders.map { order -> order.toResumedDTO() }
        )

fun User.toResumedDTO(): ResumedUserDTO =
        ResumedUserDTO(
                name = name,
                cpf = cpf.value
        )

fun SessionOrder.toResumedDTO(): ResumedSessionOrderDTO =
        ResumedSessionOrderDTO(
                order = order.toResumedDTO(),
                valuePerUser = valuePerUser(),
                users = users.map { user -> user.toResumedDTO() }
        )

fun Order.toResumedDTO(): ResumedOrderDTO =
        ResumedOrderDTO(
                name = name,
                value = value,
        )




















