package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.utils.DataHoraUtils
import br.com.gusta.barbearia.utils.StringUtils
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

data class AgendamentoDetalhesResponse(
    val cliente: String,
    val servicos: List<AgendamentoServicosResponse>,
    val barbeiro: String,
    val horario: String,
    val status: String
) : RepresentationModel<AgendamentoDetalhesResponse>() {

    companion object Mapper {
        fun paraResposta(agendamento: Agendamento): AgendamentoDetalhesResponse {
            return AgendamentoDetalhesResponse(
                agendamento.cliente.nome,
                agendamento.obterServicos().map(AgendamentoServicosResponse.Mapper::paraResposta),
                agendamento.barbeiro.nome,
                DataHoraUtils.formatar(agendamento.horario),
                StringUtils.capitalize(agendamento.status.toString())
            ).add(
                linkTo(
                    methodOn(
                        AgendamentoController::class.java
                    ).consultarAgendamento(agendamento.id!!)
                ).withSelfRel()
            )
        }
    }

}
