package br.com.gusta.barbearia.validacao

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDate

class DataPassadaValidator : ConstraintValidator<DataPassada, LocalDate> {

    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext?): Boolean {
        return value?.isBefore(LocalDate.now()) ?: false
    }

}
