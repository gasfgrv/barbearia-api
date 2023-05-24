package br.com.gusta.barbearia.servico

import java.math.BigDecimal
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

data class ServicoResponse(
    val nome: String,
    val descricao: String,
    val preco: BigDecimal,
    val duracacao: Int
) : RepresentationModel<ServicoResponse>() {

    companion object Mapper {
        fun paraResposta(servico: Servico): ServicoResponse {
            val resposta = ServicoResponse(
                servico.nome,
                servico.descricao,
                servico.preco,
                servico.duracacao
            )

            addLinks(resposta, servico.id!!)

            return resposta
        }

        private fun addLinks(resposta: ServicoResponse, id: Long) {
            val controllerClass = ServicoController::class.java
            resposta.add(linkTo(methodOn(controllerClass).detalharServico(id)).withSelfRel())
            resposta.add(linkTo(methodOn(controllerClass).listarServicos()).withRel("todos"))
            resposta.add(linkTo(methodOn(controllerClass).desativarServico(id)).withRel("desativar"))
            resposta.add(linkTo(methodOn(controllerClass).alterarServico(null)).withRel("alterar"))
        }

    }

}
