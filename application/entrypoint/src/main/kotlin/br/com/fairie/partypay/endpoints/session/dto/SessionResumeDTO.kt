package br.com.fairie.partypay.endpoints.session.dto

class SessionResumeDTO(
        val userList: List<ResumedSessionUserDTO>,
        val check: Double
)