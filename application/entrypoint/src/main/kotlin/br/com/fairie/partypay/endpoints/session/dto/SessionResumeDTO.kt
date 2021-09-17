package br.com.fairie.partypay.endpoints.session.dto

class SessionResumeDTO(
    val userList: List<SessionUserDTO>,
    val check: Double
)