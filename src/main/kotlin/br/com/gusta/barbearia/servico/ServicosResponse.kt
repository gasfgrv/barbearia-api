package br.com.gusta.barbearia.servico

import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

data class ServicosResponse(
    val nome: String,
    val descricao: String
) : RepresentationModel<ServicosResponse>() {

    companion object Mapper {
        fun paraResposta(servico: Servico): ServicosResponse {
            val resposta = ServicosResponse(
                servico.nome,
                servico.descricao
            )

            resposta.add(linkTo(methodOn(ServicoController::class.java).detalharServico(servico.id!!)).withSelfRel())

            return resposta
        }
    }

}
