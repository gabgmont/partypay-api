package br.com.fairie.partypay.endpoints.session.form

import com.fasterxml.jackson.annotation.JsonProperty

class CPFListForm(
        @JsonProperty(value = "cpf_list")
        val cpfList: List<String>
)