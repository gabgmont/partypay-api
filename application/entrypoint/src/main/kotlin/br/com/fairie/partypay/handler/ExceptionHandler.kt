package br.com.fairie.partypay.handler

import br.com.fairie.partypay.exception.*
import br.com.fairie.partypay.handler.dto.ErrorDto
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(1)
@RestControllerAdvice
class ExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(exception: BadRequestException): ErrorDto {
        return ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorDto {
        return ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InconsistenceException::class)
    fun handleInconsistenceException(exception: InconsistenceException): ErrorDto {
        return ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLCallException::class)
    fun handleSQLCallException(exception: SQLCallException): ErrorDto {
        return ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyListException::class)
    fun handleEmptyListException(exception: EmptyListException): ErrorDto {
        return ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ErrorDto {
        return ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.message.toString()
        )
    }
}