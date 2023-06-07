package br.com.gusta.barbearia.validacao

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NomeCompletoValidator::class])
@MustBeDocumented
annotation class NomeCompleto(
    val message: String = "Informe o nome completo",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)
