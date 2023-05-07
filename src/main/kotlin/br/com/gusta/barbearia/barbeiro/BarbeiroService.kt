package br.com.gusta.barbearia.barbeiro

import br.com.gusta.barbearia.agendamento.Agendamento
import br.com.gusta.barbearia.servico.Servico
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class BarbeiroService(
    private val barbeiroRepository: BarbeiroRepository
) {

    fun buscarBarbeiro(id: UUID): Barbeiro {
        return barbeiroRepository.findById(id)
            .orElseThrow { RuntimeException() }
    }

    fun verificaSeBarbeiroVaiEstarOcupadoNoHorario(horarioInicio: LocalDateTime, barbeiroId: UUID): Boolean {
        val barbeiro = buscarBarbeiro(barbeiroId)
        val agendamentosDoDia = filtraAgendamentosPorData(barbeiro, horarioInicio)
        val horariosDoBarbeiro = getHorariosDoBarbeiro(
            agendamentosDoDia,
            getHorarioDeInicioDeCadaAgendamento(agendamentosDoDia),
            getHorarioDeTerminoDeCadaAgendamento(agendamentosDoDia)
        )
        return verificaSeHorarioInicioEhDuranteAlgumAgendamentoDoBarbeiro(
            agendamentosDoDia,
            horarioInicio,
            horariosDoBarbeiro
        )
    }

    private fun filtraAgendamentosPorData(barbeiro: Barbeiro, horarioInicio: LocalDateTime): List<Agendamento> {
        return barbeiro.agendamentos.filter { agendamento ->
            agendamento.horario.toLocalDate() == horarioInicio.toLocalDate()
        }
    }

    private fun getHorariosDoBarbeiro(
        listaDeagendamentosDoDia: List<Agendamento>,
        horarioDeInicioDosAgendamentos: List<LocalDateTime>,
        horarioDeTerminoDosAgendamentos: List<LocalDateTime>
    ): HashMap<UUID, Pair<LocalDateTime, LocalDateTime>> {

        val agendamentosBarbeiro = HashMap<UUID, Pair<LocalDateTime, LocalDateTime>>()

        listaDeagendamentosDoDia.forEachIndexed { index, _ ->
            agendamentosBarbeiro[listaDeagendamentosDoDia[index].id!!] =
                Pair(horarioDeInicioDosAgendamentos[index], horarioDeTerminoDosAgendamentos[index])
        }

        return agendamentosBarbeiro
    }

    private fun getHorarioDeInicioDeCadaAgendamento(agendamentosDoDia: List<Agendamento>): List<LocalDateTime> {
        return agendamentosDoDia.map { agendamento ->
            agendamento.horario
        }
    }

    private fun getHorarioDeTerminoDeCadaAgendamento(agendamentosDoDia: List<Agendamento>): List<LocalDateTime> {
        return agendamentosDoDia.map { agendamento ->
            calcularHorarioDeTermino(agendamento.horario, agendamento.obterServicos())
        }
    }

    private fun calcularHorarioDeTermino(horario: LocalDateTime, servicos: List<Servico>): LocalDateTime {
        val duracaoTotal = servicos.map { servico ->
            servico.duracacao
        }.reduce { acc, i ->
            acc + i
        }

        return horario.plusMinutes(duracaoTotal.toLong())
    }

    private fun verificaSeHorarioInicioEhDuranteAlgumAgendamentoDoBarbeiro(
        agendamentosDoDia: List<Agendamento>,
        horarioInicio: LocalDateTime,
        horariosDoBarbeiro: HashMap<UUID, Pair<LocalDateTime, LocalDateTime>>
    ): Boolean {
        return agendamentosDoDia.any { agendamento ->
            horarioInicio >= horariosDoBarbeiro[agendamento.id]!!.first &&
                    horarioInicio <= horariosDoBarbeiro[agendamento.id]!!.second
        }
    }

}
