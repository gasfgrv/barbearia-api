package br.com.gusta.barbearia.validacao

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ListaNaoVaziaValidator::class])
@MustBeDocumented
annotation class ListaNaoVazia(
    val message: String = "Esse campo deve ter pelo menos um item",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = []
)
