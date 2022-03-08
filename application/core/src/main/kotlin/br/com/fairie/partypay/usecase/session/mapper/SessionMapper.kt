package br.com.fairie.partypay.usecase.session.mapper

import br.com.fairie.partypay.exception.InconsistenceException
import br.com.fairie.partypay.usecase.session.model.*
import java.math.BigDecimal
import java.math.RoundingMode

fun Session.calculateSessionResume(userList: List<SessionUser>): SessionResume {
    var check = BigDecimal.ZERO

    orders.forEach{ sessionOrder ->
        if (sessionOrder.status == SessionOrderStatus.CANCELED) return@forEach

        check = check.add(sessionOrder.order.value)
        sessionOrder.users.forEach { user ->
            userList.forEach { sessionUser ->
                if(user.cpf.value == sessionUser.user.cpf.value) {
                    val resumeOrder = SessionResumeOrder(sessionOrder.order, sessionOrder.valuePerUser())
                    sessionUser.orders.add(resumeOrder)
                    sessionUser.totalValue = sessionUser.totalValue.add(sessionOrder.order.value.divide(BigDecimal(sessionOrder.users.size), RoundingMode.HALF_UP))
                }
            }
        }
    }

    return SessionResume(menuId, status, check, userList)
}

fun Session.isOpen(): Boolean = status == SessionStatus.OPEN

fun Session.isClosed(): Boolean = status == SessionStatus.CLOSED

fun Session.close(){
    if (isOpen()) this.status = SessionStatus.CLOSED
    else throw InconsistenceException("Session is already closed.")
}

fun SessionOrder.valuePerUser(): BigDecimal {
    if (status == SessionOrderStatus.CANCELED) return BigDecimal.ZERO

    val divide = BigDecimal(users.size)
    return order.value.divide(divide, RoundingMode.HALF_UP)
}
