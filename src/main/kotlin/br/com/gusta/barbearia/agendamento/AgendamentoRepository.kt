package br.com.gusta.barbearia.agendamento

import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface AgendamentoRepository : JpaRepository<Agendamento, UUID> {
    fun existsAgendamentoByHorarioAndBarbeiroId(horario: LocalDateTime, barbeiroId: UUID): Boolean
}