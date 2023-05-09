package br.com.gusta.barbearia.validacao

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UuidPatternValidator::class])
@MustBeDocumented
annotation class UuidPattern(
    val message: String = "Informe o valor sendo um UUID",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)
