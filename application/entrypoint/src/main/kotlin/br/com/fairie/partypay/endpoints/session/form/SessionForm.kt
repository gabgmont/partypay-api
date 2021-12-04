package br.com.fairie.partypay.endpoints.session.form

import com.fasterxml.jackson.annotation.JsonProperty

class SessionForm(
        @JsonProperty(value = "restaurant")
        val restaurant: String,
        @JsonProperty(value = "table")
        val table: Int
)