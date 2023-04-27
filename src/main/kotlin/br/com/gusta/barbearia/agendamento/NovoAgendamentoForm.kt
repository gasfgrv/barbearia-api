package br.com.gusta.barbearia.agendamento

import java.time.LocalDateTime
import java.util.UUID

data class NovoAgendamentoForm(
        val cliente: UUID,
        val servicos: List<Long>,
        val barbeiro: UUID,
        val horario: LocalDateTime
)
