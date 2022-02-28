package br.com.fairie.partypay.endpoints.session.controller

import br.com.fairie.partypay.endpoints.session.dto.ResumedSessionDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionDTO
import br.com.fairie.partypay.endpoints.session.dto.SessionResumeDTO
import br.com.fairie.partypay.endpoints.session.form.CPFListForm
import br.com.fairie.partypay.endpoints.session.form.SessionForm
import br.com.fairie.partypay.endpoints.session.mapper.toCPFList
import br.com.fairie.partypay.endpoints.session.mapper.toDTO
import br.com.fairie.partypay.endpoints.session.mapper.toResumedDTO
import br.com.fairie.partypay.endpoints.session.mapper.toVo
import br.com.fairie.partypay.usecase.session.SessionUseCase
import br.com.fairie.partypay.usecase.session.vo.SessionOrderStatus
import br.com.fairie.partypay.usecase.session.vo.SessionStatus
import br.com.fairie.partypay.utils.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/session")
@Api(
        description = SESSION_TAG_DESCRIPTION,
        tags = [SESSION_TAG_TITLE]
)
class SessionController(
        val useCase: SessionUseCase
) {

    @PostMapping("/create")
    @ApiOperation(value = CREATE_SESSION_OPERATION_VALUE, notes = CREATE_SESSION_OPERATION_NOTES)
    fun createSession(@RequestBody sessionForm: SessionForm): ResponseEntity<SessionDTO> {
        val session = sessionForm.toVo(SessionStatus.OPEN)
        val createdSession = useCase.createSession(session).toDTO()

        return ResponseEntity.ok(createdSession)
    }

    @GetMapping("/{sessionId}")
    @ApiOperation(value = GET_SESSION_OPERATION_VALUE, notes = GET_SESSION_OPERATION_NOTES)
    fun getSession(
            @PathVariable sessionId: Long
    ): ResponseEntity<SessionDTO> {
        val createdSession = useCase.getSession(sessionId).toDTO()

        return ResponseEntity.ok(createdSession)
    }

    @PutMapping("/{sessionId}/add/order/{orderName}")
    @ApiOperation(value = ADD_ORDER_SESSION_OPERATION_VALUE, notes = ADD_ORDER_SESSION_OPERATION_NOTES)
    fun addOrder(
            @PathVariable sessionId: Long,
            @PathVariable orderName: String,
            @RequestBody cpfListForm: CPFListForm
    ): ResponseEntity<ResumedSessionDTO> {
        val cpfList = cpfListForm.toCPFList()
        val session = useCase.addOrder(sessionId, orderName, cpfList).toResumedDTO()

        return ResponseEntity.ok(session)
    }

    @PutMapping("/{sessionId}/update/order/{orderId}/status/{status}")
    @ApiOperation(value = UPDATE_ORDER_STATUS_SESSION_OPERATION_VALUE, notes = UPDATE_ORDER_STATUS_SESSION_OPERATION_NOTES)
    fun cancelOrder(
            @PathVariable sessionId: Long,
            @PathVariable orderId: Long,
            @PathVariable status: SessionOrderStatus
    ): ResponseEntity<ResumedSessionDTO> {
        val session = useCase.updateOrderStatus(sessionId, orderId, status).toResumedDTO()

        return ResponseEntity.ok(session)
    }

    @PutMapping("/{sessionId}/add/user/cpf")
    @ApiOperation(value = ADD_USER_SESSION_OPERATION_VALUE, notes = ADD_USER_SESSION_OPERATION_NOTES)
    fun addUser(
            @PathVariable sessionId: Long,
            @RequestBody cpfListForm: CPFListForm
    ): ResponseEntity<ResumedSessionDTO> {

        val cpfList = cpfListForm.toCPFList()
        val session = useCase.addUser(sessionId, cpfList).toResumedDTO()

        return ResponseEntity.ok(session)
    }

    @GetMapping("/resume/{sessionId}")
    @ApiOperation(value = SESSION_RESUME_OPERATION_VALUE, notes = SESSION_RESUME_OPERATION_NOTES)
    fun sessionResume(@PathVariable sessionId: Long): ResponseEntity<SessionResumeDTO>{
        val sessionResume = useCase.getResume(sessionId).toDTO()
        return ResponseEntity.ok(sessionResume)
    }

    @PutMapping("/{sessionId}/close")
    @ApiOperation(value = CLOSE_SESSION_OPERATION_VALUE, notes = CLOSE_SESSION_OPERATION_NOTES)
    fun closeSession(
            @PathVariable sessionId: Long,
            @RequestParam(required = false, defaultValue = "false") forceClose: Boolean
    ): ResponseEntity<SessionResumeDTO> {
        val endedSession = useCase.endSession(sessionId, forceClose).toDTO()

        return ResponseEntity.ok(endedSession)
    }
}
