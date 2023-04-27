package br.com.gusta.barbearia.agendamento

import java.time.LocalDateTime
import org.springframework.hateoas.RepresentationModel

data class NovoAgendamentoResponse(
    val servicos: List<NovoAgendamentoServicosResponse>,
    val barbeiro: String,
    val horario: LocalDateTime
) : RepresentationModel<NovoAgendamentoResponse>() {

    companion object Mapper {
        fun paraResposta(agendamento: Agendamento): NovoAgendamentoResponse {
            return NovoAgendamentoResponse(
                agendamento.obterServicos().map(NovoAgendamentoServicosResponse.Mapper::paraResposta),
                agendamento.barbeiro.nome,
                agendamento.horario
            )
        }
    }

}
