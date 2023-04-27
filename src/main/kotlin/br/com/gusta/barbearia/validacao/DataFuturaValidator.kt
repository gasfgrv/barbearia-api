package br.com.gusta.barbearia.validacao

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDateTime

class DataFuturaValidator : ConstraintValidator<DataFutura, LocalDateTime> {

    override fun isValid(value: LocalDateTime?, context: ConstraintValidatorContext?): Boolean {
        return value?.isAfter(LocalDateTime.now()) ?: false
    }

}
