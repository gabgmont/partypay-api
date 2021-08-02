package br.com.fairie.partypay.endpoints.handler

import br.com.fairie.partypay.endpoints.handler.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BadRequestExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(exception: MethodArgumentNotValidException): ErrorDto{
        return ErrorDto(
            HttpStatus.BAD_REQUEST.value(),
            exception.message
        )
    }
}