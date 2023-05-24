package br.com.gusta.barbearia.servico

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive

data class AlterarServicoForm(
    @Positive(message = "O id deve ser maior que 0")
    @NotNull(message = "O id não pode ser nulo")
    val id: Long,

    @NotNull(message = "Nome não pode ser nulo")
    @NotEmpty(message = "Nome não pode estar em branco")
    val nome: String,

    @NotNull(message = "Descrição não pode ser nulo")
    @NotEmpty(message = "Descrição não pode estar em branco")
    val descricao: String,

    @Pattern(regexp = "\\d{1,3},\\d{2}", message = "O preço deve seguir o seguinte padão \\d{1,3},\\d{2}")
    val preco: String,

    @Positive(message = "A duração deve ser maior que 0")
    @NotNull(message = "A duração não pode ser nula")
    val duracao: Int
)
