package br.com.fairie.partypay.endpoints.session.mapper

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionResumeDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionOrderDTO
import br.com.fairie.partypay.endpoints.session.form.CPFListForm
import br.com.fairie.partypay.endpoints.session.form.SessionForm
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.usecase.session.mapper.valuePerUser
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrder
import br.com.fairie.partypay.usecase.session.vo.SessionResume
import br.com.fairie.partypay.usecase.session.vo.SessionStatus
import br.com.fairie.partypay.usecase.user.vo.User
import br.com.fairie.partypay.vo.CPF

fun CPFListForm.toCPFList(): List<CPF> {
    val cpfs = ArrayList<CPF>()

    cpfList.forEach { cpf ->
        cpfs.add(CPF(cpf))
    }
    return cpfs
}

fun SessionForm.toVo(status: SessionStatus): Session {
    return Session(
        id = null,
        restaurant = restaurant,
        table = table,
        status = status,
        users = arrayListOf(),
        orders = arrayListOf()
    )
}

fun Session.toDTO(): SessionDTO {
    val usersDTO = users.toUserDTOList()
    val ordersDTO = orders.toSessionOrderDTOList()

    return SessionDTO(
        id = id,
        restaurant = restaurant,
        table = table,
        userList = usersDTO,
        orderList = ordersDTO
    )
}

fun MutableList<User>.toUserDTOList(): List<UserDTO> {
    val userList = ArrayList<UserDTO>()

    forEach { user ->
        userList.add(
            UserDTO(
                name = user.name,
                cpf = user.cpf.value,
                email = user.email.value,
                phone = user.phone.value,
                photo = user.photo?.value ?: ""
            )
        )
    }

    return userList
}

fun MutableList<SessionOrder>.toSessionOrderDTOList(): List<SessionOrderDTO> {
    val sessionOrderList = ArrayList<SessionOrderDTO>()

    forEach { sessionOrder ->
        val orderDTO = OrderDTO(
            name = sessionOrder.order.name,
            description = sessionOrder.order.description,
            value = sessionOrder.order.value
        )

        val userDTOList = ArrayList<UserDTO>()

        sessionOrder.users.forEach { user ->
            userDTOList.add(
                UserDTO(
                    name = user.name,
                    cpf = user.cpf.value,
                    email = user.email.value,
                    phone = user.phone.value,
                    photo = user.photo?.value ?: ""
                )
            )
        }
        sessionOrderList.add(
            SessionOrderDTO(
                order = orderDTO,
                valuePerUser = sessionOrder.valuePerUser(),
                users = userDTOList
            )
        )
    }
    return sessionOrderList
}

fun SessionResume.toDTO(): SessionResumeDTO {
    val userResumeList = orders.toSessionOrderDTOList()

    return SessionResumeDTO(
        userList = userResumeList,
        check = check.toDouble()
    )
}


























