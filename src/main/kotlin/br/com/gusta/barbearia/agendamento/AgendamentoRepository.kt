package br.com.gusta.barbearia.agendamento

import java.util.*
import org.springframework.data.jpa.repository.JpaRepository

interface AgendamentoRepository : JpaRepository<Agendamento, UUID> {
}