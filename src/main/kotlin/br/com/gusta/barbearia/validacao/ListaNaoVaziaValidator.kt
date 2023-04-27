package br.com.gusta.barbearia.validacao

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class ListaNaoVaziaValidator : ConstraintValidator<ListaNaoVazia, List<Any>> {

    override fun isValid(value: List<Any>?, context: ConstraintValidatorContext?): Boolean {
        return value?.isNotEmpty() ?: false
    }

}