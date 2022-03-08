package br.com.fairie.partypay.endpoints.session.mapper

import br.com.fairie.partypay.endpoints.menu.dto.ResumedOrderDTO
import br.com.fairie.partypay.endpoints.menu.mapper.toDTO
import br.com.fairie.partypay.endpoints.session.dto.*
import br.com.fairie.partypay.endpoints.session.form.CPFListForm
import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO
import br.com.fairie.partypay.endpoints.user.mapper.toDTO
import br.com.fairie.partypay.usecase.menu.model.Order
import br.com.fairie.partypay.usecase.session.mapper.valuePerUser
import br.com.fairie.partypay.usecase.session.model.*
import br.com.fairie.partypay.usecase.user.model.User
import br.com.fairie.partypay.vo.CPF

fun CPFListForm.toCPFList(): List<CPF> = cpfList.map { cpf -> CPF(cpf) }

fun Session.toDTO(): SessionDTO = SessionDTO(
        id = id,
        restaurant = restaurant,
        table = table,
        status = status,
        users = users.map { user -> user.toDTO() },
        orders = orders.map { sessionOrder -> sessionOrder.toDTO() }
)

fun SessionOrder.toDTO(): SessionOrderDTO {
    val userList =
            if (status == SessionOrderStatus.CANCELED) arrayListOf()
            else users.map { user -> user.toDTO() }

    return SessionOrderDTO(
            order = order.toDTO(),
            status = status,
            users = userList,
            valuePerUser = valuePerUser()
    )
}

fun SessionResume.toDTO(): SessionResumeDTO = SessionResumeDTO(
        users = users.map { user -> user.toResumedDTO() },
        status = status,
        check = check.toDouble()
)

fun SessionUser.toResumedDTO(): ResumedSessionUserDTO = ResumedSessionUserDTO(
        user = user.toResumedDTO(),
        orders = orders.map { resumeOrder -> resumeOrder.toResumeOrderDTO() }.toMutableList(),
        totalValue = totalValue
)

fun SessionResumeOrder.toResumeOrderDTO() =
        SessionResumeOrderDTO(
                resumeOrder = order.toResumedDTO(),
                valuePerUser =  valuePerUser
        )

fun Session.toResumedDTO(): ResumedSessionDTO =
        ResumedSessionDTO(
                id = id,
                restaurant = restaurant,
                table = table,
                users = users.map { user -> user.toResumedDTO() },
                orders = orders.map { order -> order.toResumedDTO() }
        )

fun User.toResumedDTO(): ResumedUserDTO =
        ResumedUserDTO(
                name = name,
                cpf = cpf.value
        )

fun SessionOrder.toResumedDTO(): ResumedSessionOrderDTO {

    val userList =
            if (status == SessionOrderStatus.CANCELED) arrayListOf()
            else users.map { user -> user.toResumedDTO() }

    return ResumedSessionOrderDTO(
            order = order.toResumedDTO(),
            status = status,
            valuePerUser = valuePerUser(),
            users = userList

    )
}

fun Order.toResumedDTO(): ResumedOrderDTO =
        ResumedOrderDTO(
                name = name,
                value = value,
        )
