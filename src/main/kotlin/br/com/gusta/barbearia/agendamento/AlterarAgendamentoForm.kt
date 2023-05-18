package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.validacao.DataFutura
import br.com.gusta.barbearia.validacao.UuidPattern
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.util.UUID

data class AlterarAgendamentoForm(
        @UuidPattern
        val agendamento: UUID,

        @NotNull(message = "Informe a nova data do agendamento")
        @DataFutura(message = "Informe uma data futura para o agendamento")
        val novoHorario: LocalDateTime
)
