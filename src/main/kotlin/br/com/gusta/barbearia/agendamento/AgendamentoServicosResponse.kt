package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.servico.Servico
import br.com.gusta.barbearia.servico.ServicoController
import java.math.BigDecimal
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

data class AgendamentoServicosResponse(
    val nome: String,
    val preco: BigDecimal,
    val duracacao: Int
) : RepresentationModel<AgendamentoServicosResponse>() {

    companion object Mapper {

        fun paraResposta(servico: Servico): AgendamentoServicosResponse {
            val resposta = AgendamentoServicosResponse(
                servico.nome,
                servico.preco,
                servico.duracacao
            )

            resposta.add(linkTo(methodOn(ServicoController::class.java).detalharServico(servico.id!!)).withSelfRel())

            return resposta
        }

    }

}
