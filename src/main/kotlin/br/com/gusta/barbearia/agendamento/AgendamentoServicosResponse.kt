package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.servico.Servico
import br.com.gusta.barbearia.servico.ServicoController
import java.math.BigDecimal
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

data class NovoAgendamentoServicosResponse(
    val nome: String,
    val preco: BigDecimal,
    val duracacao: Int
) : RepresentationModel<NovoAgendamentoServicosResponse>() {

    companion object Mapper {
        fun paraResposta(servico: Servico): NovoAgendamentoServicosResponse {
            val controllerClass = ServicoController::class.java
            return NovoAgendamentoServicosResponse(
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
