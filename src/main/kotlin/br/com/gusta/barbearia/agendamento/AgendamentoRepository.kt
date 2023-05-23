package br.com.gusta.barbearia.agendamento

import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AgendamentoRepository : JpaRepository<Agendamento, UUID> {

    @Query(
        value = "select exists(select 1 from agendamento where horario=:horario and barbeiro=:barbeiroId and status in ('AGENDADO', 'CONCLUIDO'))",
        nativeQuery = true
    )
    fun existeAgendamentoNoHorarioParaBarbeiro(horario: LocalDateTime, barbeiroId: UUID): Boolean

}
