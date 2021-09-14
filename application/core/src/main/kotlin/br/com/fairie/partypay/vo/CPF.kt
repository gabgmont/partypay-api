package br.com.fairie.partypay.vo

import java.util.regex.Pattern

class CPF(
    var value: String
) {

    init {
        value = format(value)
    }

    private fun format(cpf: String): String{
        val pattern: Pattern = Pattern.compile("(\\d{3})(\\d{3})(\\d{3})(\\d{2})")
        val matcher = pattern.matcher(cpf)
        if(matcher.find()){
            return matcher.replaceAll("$1.$2.$3-$4")
        }
        return cpf
    }

    fun longValue(): Long {
        return value.replace(".", "").replace("-", "").toLong()
    }
}