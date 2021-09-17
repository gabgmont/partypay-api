package br.com.fairie.partypay.usecase.session.mapper

import br.com.fairie.partypay.exception.SessionStatusException
import br.com.fairie.partypay.usecase.session.vo.Session
import br.com.fairie.partypay.usecase.session.vo.SessionResume
import br.com.fairie.partypay.usecase.session.vo.SessionStatus
import java.math.BigDecimal

fun Session.calculateSessionResume(): SessionResume {
    val check = BigDecimal.ZERO

    orders.forEach{ sessionOrder ->
        check.add(sessionOrder.value)
    }

    return SessionResume(users, check)
}

fun Session.isOpen(): Boolean = status == SessionStatus.OPEN

fun Session.isClosed(): Boolean = status == SessionStatus.CLOSED

fun Session.close(){
    if (isOpen()) this.status = SessionStatus.CLOSED
    else throw SessionStatusException("Session is already closed.")
}