package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.utils.DataHoraUtils
import br.com.gusta.barbearia.utils.StringUtils
import java.util.UUID
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
            val resposta = AgendamentoDetalhesResponse(
                agendamento.cliente.nome,
                agendamento.obterServicos().map(AgendamentoServicosResponse.Mapper::paraResposta),
                agendamento.barbeiro.nome,
                DataHoraUtils.formatar(agendamento.horario),
                StringUtils.capitalize(agendamento.status.toString())
            )

            addLinks(resposta, agendamento.id!!)

            return resposta
        }

        private fun addLinks(resposta: AgendamentoDetalhesResponse, agendamento: UUID) {
            val controllerClass = AgendamentoController::class.java
            resposta.add(linkTo(methodOn(controllerClass).consultarAgendamento(agendamento)).withSelfRel())
                .add(linkTo(methodOn(controllerClass).concluirAgendamento(agendamento)).withRel("concluir"))
                .add(linkTo(methodOn(controllerClass).cancelarAgendamento(agendamento)).withRel("cancelar"))
                .add(linkTo(methodOn(controllerClass).alterarAgendamento(null)).withRel("remarcar"))
        }
    }

}
