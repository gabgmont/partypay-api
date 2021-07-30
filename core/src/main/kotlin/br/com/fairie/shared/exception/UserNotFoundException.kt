package br.com.fairie.shared.exception

import java.lang.RuntimeException

class UserNotFoundException(message: String) : RuntimeException(message)