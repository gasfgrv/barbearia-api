package br.com.gusta.barbearia.validacao

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.UUID

private const val UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"

class UuidPatternValidator : ConstraintValidator<UuidPattern, UUID> {

    override fun isValid(uuid: UUID?, context: ConstraintValidatorContext?): Boolean {
        return uuid?.toString()?.matches(UUID_PATTERN.toRegex()) ?: false
    }

}