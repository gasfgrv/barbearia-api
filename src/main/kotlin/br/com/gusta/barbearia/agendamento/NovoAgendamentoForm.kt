package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.validacao.DataFutura
import br.com.gusta.barbearia.validacao.ListaNaoVazia
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime
import java.util.UUID

private const val UUID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"

data class NovoAgendamentoForm(
    @Pattern(regexp = UUID_PATTERN)
    val cliente: UUID,

    @ListaNaoVazia(message = "Adicione pelo menos um serviço")
    val servicos: List<Long>,

    @Pattern(regexp = UUID_PATTERN)
    val barbeiro: UUID,

    @NotNull(message = "Informe a data do agendamento")
    @DataFutura(message = "Informe uma data futura para o agendamento")
    val horario: LocalDateTime
)