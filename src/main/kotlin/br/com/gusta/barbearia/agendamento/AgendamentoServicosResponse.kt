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
            val controllerClass = ServicoController::class.java
            return AgendamentoServicosResponse(
                servico.nome,
                servico.preco,
                servico.duracacao
            ).add(
                linkTo(
                    methodOn(
                        controllerClass
                    ).DetalharServico(servico.id)
                ).withSelfRel()
            ).add(
                linkTo(
                    methodOn(
                        controllerClass
                    ).listarServicos()
                ).withRel("servicos")
            )
        }
    }

}
