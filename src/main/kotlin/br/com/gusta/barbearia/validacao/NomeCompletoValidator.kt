package br.com.gusta.barbearia.validacao

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NomeCompletoValidator : ConstraintValidator<NomeCompleto, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        val regex = Regex("\\s")
        val contemNomeSobrenome = value?.contains(regex) ?: false
        val nomes = value?.split(regex)
        return  contemNomeSobrenome && validaNomeSobrenome(nomes)
    }

    private fun validaNomeSobrenome(nomes: List<String>?) =
        nomes?.stream()?.allMatch {
            it.matches(Regex("^[-A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ']+$"))
        } ?: false

}
