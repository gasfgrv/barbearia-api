package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.validacao.DataFutura
import br.com.gusta.barbearia.validacao.ListaNaoVazia
import br.com.gusta.barbearia.validacao.UuidPattern
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.UUID

data class NovoAgendamentoForm(
    @UuidPattern
    val cliente: UUID,

    @ListaNaoVazia(message = "Adicione pelo menos um serviço")
    val servicos: List<Long>,

    @UuidPattern
    val barbeiro: UUID,

    @NotNull(message = "Informe a data do agendamento")
    @DataFutura(message = "Informe uma data futura para o agendamento")
    val horario: LocalDateTime
)