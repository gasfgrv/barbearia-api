package br.com.gusta.barbearia.cliente

import br.com.gusta.barbearia.validacao.NomeCompleto
import br.com.gusta.barbearia.validacao.UuidPattern
import jakarta.validation.constraints.Pattern
import java.util.UUID

data class AtualizarDadosForm(
    @UuidPattern
    val id: UUID,
    @NomeCompleto
    val nome: String,
    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}", message = "Informe um número de celular ou fixo")
    val telefone: String,
)
