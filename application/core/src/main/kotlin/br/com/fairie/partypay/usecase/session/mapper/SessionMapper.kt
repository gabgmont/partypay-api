package br.com.fairie.partypay.usecase.session.mapper

import br.com.fairie.partypay.exception.SessionStatusException
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionOrder
import br.com.fairie.partypay.usecase.session.vo.SessionResume
import br.com.fairie.partypay.usecase.session.vo.SessionStatus
import java.math.BigDecimal
import java.math.RoundingMode

fun Session.calculateSessionResume(): SessionResume {
    val check = BigDecimal.ZERO

    orders.forEach{ sessionOrder ->
        check.add(sessionOrder.order.value)
    }

    return SessionResume(orders, check)
}

fun Session.isOpen(): Boolean = status == SessionStatus.OPEN

fun Session.isClosed(): Boolean = status == SessionStatus.CLOSED

fun Session.close(){
    if (isOpen()) this.status = SessionStatus.CLOSED
    else throw SessionStatusException("Session is already closed.")
}

fun SessionOrder.valuePerUser(): BigDecimal {
    val divide = BigDecimal(users.size)
    return order.value.divide(divide, RoundingMode.HALF_UP)
}
