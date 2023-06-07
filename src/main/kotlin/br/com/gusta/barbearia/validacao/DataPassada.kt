package br.com.gusta.barbearia.validacao

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DataPassadaValidator::class])
@MustBeDocumented
annotation class DataPassada(
    val message: String = "Informe uma data anterior a de hoje",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)
