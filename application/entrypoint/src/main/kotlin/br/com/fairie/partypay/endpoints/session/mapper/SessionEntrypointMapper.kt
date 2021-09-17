package br.com.fairie.partypay.endpoints.session.mapper

import br.com.fairie.partypay.endpoints.menu.dto.OrderDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionResumeDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionUserDTO
import br.com.fairie.partypay.endpoints.session.form.CPFListForm
import br.com.fairie.partypay.endpoints.session.form.SessionForm
import br.com.fairie.partypay.endpoints.user.dto.UserDTO
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionResume
import br.com.fairie.partypay.usecase.session.vo.SessionStatus
import br.com.fairie.partypay.vo.CPF

fun CPFListForm.toCPFList(): List<CPF>{
    val cpfs = ArrayList<CPF>()

    cpfList.forEach { cpf ->
        cpfs.add(CPF(cpf))
    }
    return cpfs
}

fun SessionForm.toVo(status: SessionStatus): Session {
    return Session(
        id = id,
        restaurant = restaurant,
        table = table,
        menu = menu,
        status = status,
        users = arrayListOf(),
        orders = arrayListOf()
    )
}

fun Session.toDTO(): SessionDTO {
    val usersDTO = ArrayList<UserDTO>()
    val ordersDTO = ArrayList<OrderDTO>()

    users.forEach { session ->
        usersDTO.add(
            UserDTO(
                name = session.user.name,
                cpf = session.user.cpf.value,
                email = session.user.email.value,
                phone = session.user.phone.value,
                photo = session.user.photo?.value ?: ""
            )
        )
    }

    orders.forEach { session ->
        ordersDTO.add(
            OrderDTO(
                name = session.order.name,
                description = session.order.description,
                value = session.order.value.toDouble(),
            )
        )
    }

    return SessionDTO(
        id = id,
        restaurant = restaurant,
        table = table,
        menu = menu,
        userList = usersDTO,
        orderList = ordersDTO
    )
}

fun SessionResume.toDTO(): SessionResumeDTO{
    val userResumeList = ArrayList<SessionUserDTO>()

    users.forEach { sessionUser ->

        val userDto = UserDTO(
            name = sessionUser.user.name,
            cpf = sessionUser.user.cpf.value,
            email = sessionUser.user.email.value,
            phone = sessionUser.user.phone.value,
            photo = sessionUser.user.photo?.value ?: ""
        )

        val orderDTOList = ArrayList<OrderDTO>()
        sessionUser.orders.forEach { order ->
            orderDTOList.add(
                OrderDTO(
                    name = order.name,
                    description = order.description,
                    value = order.value.toDouble()
                )
            )
        }

        userResumeList.add(
            SessionUserDTO(
                user = userDto,
                orders = orderDTOList,
                personalTotal = sessionUser.personalTotal.toDouble()
            )
        )
    }

    return SessionResumeDTO(
        userList = userResumeList,
        check = check.toDouble()
    )
}





























