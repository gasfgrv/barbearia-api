package br.com.gusta.barbearia.validacao

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.IntStream


class CPFValidator : ConstraintValidator<CPF, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        val regex = Regex("\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}")
        val cpfSegueOPadrao = value?.matches(regex) ?: false
        return cpfSegueOPadrao && cpfValido(value!!)
    }

    private fun cpfValido(numeroCpf: String): Boolean {
        val regex = Regex("[.\\-]")
        val cpfSemFormatacao = numeroCpf.replace(regex, "").trim()

        val numerosCpf = cpfSemFormatacao.map { it.toString().toInt() }.toList()

        val digitoValidador1 = calcularDigitoVerificador(numerosCpf, 9, 1)
        val digitoValidador2 = calcularDigitoVerificador(numerosCpf, 10, 0);

        return realizaValidacao(numerosCpf, digitoValidador1, digitoValidador2)
    }

    private fun calcularDigitoVerificador(numeros: List<Int>, index: Int, pesoInicial: Int): Int {
        val digitoValidador = AtomicInteger()

        IntStream.range(0, index).forEach { i: Int ->
            digitoValidador.addAndGet(numeros[i] * (i + pesoInicial))
        }

        return digitoValidador.get() % 11
    }

    private fun realizaValidacao(numeros: List<Int>, vararg digitoValidador: Int): Boolean {
        val digitoValidador1 = digitoValidador[0]
        val digitoValidador2 = digitoValidador[1]
        return numeros[9] == digitoValidador1 && numeros[10] == digitoValidador2
    }

}
