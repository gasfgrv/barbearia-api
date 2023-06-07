package br.com.gusta.barbearia.cliente

import br.com.gusta.barbearia.utils.StringUtils
import java.util.UUID
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

data class ClienteResponse(
    val nome: String,
    val login: String,
) : RepresentationModel<ClienteResponse>() {

    companion object Mapper {
        fun paraResposta(cliente: Cliente): ClienteResponse {
            val resposta = ClienteResponse(
                StringUtils.decodeDeBase64(cliente.nome),
                StringUtils.decodeDeBase64(cliente.login)
            )

            addLinks(resposta, cliente.id!!)

            return resposta
        }

        private fun addLinks(resposta: ClienteResponse, id: UUID) {
            resposta.add(linkTo(methodOn(ClienteController::class.java).detalharCliente(id)).withSelfRel())
        }
    }

}
