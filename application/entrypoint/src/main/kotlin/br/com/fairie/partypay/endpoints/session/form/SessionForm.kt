package br.com.fairie.partypay.endpoints.session.form

import com.fasterxml.jackson.annotation.JsonProperty

class SessionForm(
        @JsonProperty(value = "menu_id")
        val menuId: Long,

        @JsonProperty(value = "table")
        val table: Int,

        @JsonProperty(value = "users")
        val users: UsernameListForm
)
