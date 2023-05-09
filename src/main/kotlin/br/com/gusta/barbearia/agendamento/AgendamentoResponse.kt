package br.com.gusta.barbearia.agendamento

import java.time.LocalDateTime
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

data class AgendamentoResponse(
    val servicos: List<AgendamentoServicosResponse>,
    val barbeiro: String,
    val horario: LocalDateTime
) : RepresentationModel<AgendamentoResponse>() {

    companion object Mapper {
        fun paraResposta(agendamento: Agendamento): AgendamentoResponse {
            return AgendamentoResponse(
                agendamento.obterServicos().map(AgendamentoServicosResponse.Mapper::paraResposta),
                agendamento.barbeiro.nome,
                agendamento.horario
            ).add(
                linkTo(
                    methodOn(
                        AgendamentoController::class.java
                    ).consultarAgendamento(agendamento.id!!)
                ).withSelfRel()
            ).add(
                linkTo(
                    methodOn(
                        AgendamentoController::class.java
                    ).concluirAgendamento(agendamento.id!!)
                ).withRel("concluir")
            ).add(
                linkTo(
                    methodOn(
                        AgendamentoController::class.java
                    ).cancelarAgendamento(agendamento.id!!)
                ).withRel("cancelar")
            )
        }
    }

}
