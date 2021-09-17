package br.com.fairie.partypay.handler

import br.com.fairie.partypay.handler.dto.ErrorDto
import br.com.fairie.partypay.exception.BadRequestException
import br.com.fairie.partypay.exception.NotFoundException
import br.com.fairie.partypay.exception.SQLCallException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class HttpExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(exception: NotFoundException): ErrorDto {
        return ErrorDto(
            HttpStatus.NOT_FOUND.value(),
            exception.message!!
        )
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBadRequest(exception: MethodArgumentNotValidException): ErrorDto{
        return ErrorDto(
            HttpStatus.BAD_REQUEST.value(),
            exception.message!!
        )
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLCallException::class)
    fun handleSQLCallException(exception: SQLCallException): ErrorDto{
        return ErrorDto(
            HttpStatus.BAD_REQUEST.value(),
            exception.message!!
        )
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: BadRequestException): ErrorDto{
        return ErrorDto(
            HttpStatus.BAD_REQUEST.value(),
            exception.message!!
        )
    }
}