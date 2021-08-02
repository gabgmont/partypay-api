package br.com.fairie.partypay.endpoints.handler

import br.com.fairie.partypay.endpoints.handler.dto.ErrorDto
import br.com.fairie.partypay.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class NotFoundExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handle(exception: NotFoundException): ErrorDto {
        return ErrorDto(
            HttpStatus.NOT_FOUND.value(),
            exception.message!!
        )
    }
}