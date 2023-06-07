package br.com.gusta.barbearia.validacao

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CPFValidator::class])
@MustBeDocumented
annotation class CPF(
    val message: String = "Informe um CPF válido",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)
