package br.com.fairie.endpoints.handler

import br.com.fairie.endpoints.handler.dto.ErrorDto
import br.com.fairie.shared.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserNotFoundExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException::class)
    fun handle(exception: UserNotFoundException): ErrorDto {
        return ErrorDto(
            HttpStatus.NOT_FOUND.value(),
            exception.message!!
        )
    }
}