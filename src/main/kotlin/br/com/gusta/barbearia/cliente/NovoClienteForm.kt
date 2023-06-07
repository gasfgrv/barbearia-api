package br.com.gusta.barbearia.cliente

import br.com.gusta.barbearia.validacao.CPF
import br.com.gusta.barbearia.validacao.DataFutura
import br.com.gusta.barbearia.validacao.DataPassada
import br.com.gusta.barbearia.validacao.NomeCompleto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import jakarta.validation.constraints.Pattern
import java.time.LocalDate

data class NovoClienteForm(
    @NomeCompleto
    val nome: String,

    @CPF
    val cpf: String,

    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Informe um número de celular ou fixo")
    val telefone: String,

    @Email(message = "Informe um email válido")
    val login: String,

    @NotBlank
    val senha: String,

    @Pattern(regexp = "^[mM]$|^[fF]$", message = "Informe 'M' ou 'F'")
    val sexo: String,

    @DataPassada
    val dataNascimento: LocalDate
)
