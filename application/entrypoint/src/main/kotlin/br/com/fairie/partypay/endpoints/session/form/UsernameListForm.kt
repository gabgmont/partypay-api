package br.com.fairie.partypay.endpoints.session.form

import com.fasterxml.jackson.annotation.JsonProperty

class UsernameListForm(
        @JsonProperty(value = "username_list")
        val usernameList: List<String>
)
