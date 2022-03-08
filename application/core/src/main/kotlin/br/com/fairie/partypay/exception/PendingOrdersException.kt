package br.com.fairie.partypay.exception

import br.com.fairie.partypay.usecase.session.model.SessionOrder

class PendingOrdersException(message: String, val orders: List<SessionOrder>) : Exception(message)
