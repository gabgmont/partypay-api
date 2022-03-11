package br.com.fairie.partypay.endpoints.session.mapper

import br.com.fairie.partypay.endpoints.menu.dto.ResumedOrderDTO
import br.com.fairie.partypay.endpoints.menu.mapper.toDTO
import br.com.fairie.partypay.endpoints.session.dto.*
import br.com.fairie.partypay.endpoints.user.dto.ResumedUserDTO
import br.com.fairie.partypay.endpoints.user.mapper.toDTO
import br.com.fairie.partypay.usecase.menu.model.Order
import br.com.fairie.partypay.usecase.session.mapper.valuePerUser
import br.com.fairie.partypay.usecase.session.model.*
import br.com.fairie.partypay.usecase.user.model.User

fun Session.toDTO(): SessionDTO = SessionDTO(
        id = id,
        restaurant = restaurant,
        menuId = menuId,
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
        menuId = menuId,
        status = status,
        check = check.toDouble(),
        users = users.map { user -> user.toResumedDTO() }
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
                menuId = menuId,
                table = table,
                users = users.map { user -> user.toResumedDTO() },
                orders = orders.map { order -> order.toResumedDTO() }
        )

fun User.toResumedDTO(): ResumedUserDTO =
        ResumedUserDTO(
                name = name,
                username = username
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
                id = id,
                name = name,
                value = value,
        )
