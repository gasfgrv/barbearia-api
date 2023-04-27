package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.servico.Servico
import br.com.gusta.barbearia.servico.ServicoController
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import java.math.BigDecimal

data class NovoAgendamentoServicosResponse(
    val nome: String,
    val preco: BigDecimal,
    val duracacao: Int
) : RepresentationModel<NovoAgendamentoServicosResponse>() {

    companion object Mapper {
        fun paraResposta(servico: Servico): NovoAgendamentoServicosResponse {
            val resposta = NovoAgendamentoServicosResponse(
                servico.nome,
                servico.preco,
                servico.duracacao
            )

            val controllerClass = ServicoController::class.java
            resposta.add(linkTo(methodOn(controllerClass).DetalharServico(servico.id)).withSelfRel())
            resposta.add(linkTo(methodOn(controllerClass).listarServicos()).withRel("todos servicos"))

            return resposta
        }
    }

}
