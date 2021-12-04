package br.com.fairie.partypay.handler

import br.com.fairie.partypay.handler.dto.ErrorDTO
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(2)
@RestControllerAdvice
class GenericExceptionHandler {

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleGenericException(exception: Exception): ErrorDTO {
        return ErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                exception.message.toString()
        )
    }
}