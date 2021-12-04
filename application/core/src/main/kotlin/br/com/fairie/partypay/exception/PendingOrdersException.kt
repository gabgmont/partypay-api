package br.com.fairie.partypay.exception

import br.com.fairie.partypay.usecase.session.vo.SessionOrder

class PendingOrdersException(message: String, val orders: List<SessionOrder>) : Exception(message)