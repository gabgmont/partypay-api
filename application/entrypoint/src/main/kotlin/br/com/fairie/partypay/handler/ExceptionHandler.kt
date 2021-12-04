package br.com.fairie.partypay.handler

import br.com.fairie.partypay.endpoints.session.mapper.toResumedDTO
import br.com.fairie.partypay.exception.*
import br.com.fairie.partypay.handler.dto.ErrorDTO
import br.com.fairie.partypay.handler.dto.PendingOrdersErrorDTO
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
    fun handleBadRequest(exception: BadRequestException): ErrorDTO {
        return ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorDTO {
        return ErrorDTO(
                HttpStatus.BAD_REQUEST.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InconsistenceException::class)
    fun handleInconsistenceException(exception: InconsistenceException): ErrorDTO {
        return ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLCallException::class)
    fun handleSQLCallException(exception: SQLCallException): ErrorDTO {
        return ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyListException::class)
    fun handleEmptyListException(exception: EmptyListException): ErrorDTO {
        return ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ErrorDTO {
        return ErrorDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.message.toString()
        )
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PendingOrdersException::class)
    fun handlePendingOrdersException(exception: PendingOrdersException): PendingOrdersErrorDTO {

        return PendingOrdersErrorDTO(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = exception.message.toString(),
                orders = exception.orders.map { sessionOrder -> sessionOrder.toResumedDTO() }
        )
    }
}