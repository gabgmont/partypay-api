package br.com.fairie.partypay.handler

import br.com.fairie.partypay.exception.*
import br.com.fairie.partypay.handler.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UsecaseExceptionHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(
        SessionInconsistenceException::class,
        SessionCreationException::class,
        SessionStatusException::class,
        SQLCallException::class,
        NotFoundException::class,
        EmptyListException::class)
    fun handleNotFound(exception: Exception): ErrorDto {
        return ErrorDto(
            HttpStatus.NOT_FOUND.value(),
            exception.message!!
        )
    }
}