package br.com.gusta.barbearia.validacao

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DataFuturaValidator::class])
@MustBeDocumented
annotation class DataFutura(
    val message: String = "Informe uma data futura",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)
