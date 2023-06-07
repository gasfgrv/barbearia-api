package br.com.gusta.barbearia.cliente

import br.com.gusta.barbearia.utils.StringUtils
import java.time.LocalDate
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn

data class ClienteDetalhesResponse(
    val nome: String,
    val cpf: String,
    val telefone: String,
    val login: String,
    val sexo: String,
    val dataNascimento: LocalDate
) : RepresentationModel<ClienteDetalhesResponse>() {

    companion object Mapper {
        fun paraResposta(cliente: Cliente): ClienteDetalhesResponse {
            val resposta = ClienteDetalhesResponse(
                StringUtils.decodeDeBase64(cliente.nome),
                StringUtils.decodeDeBase64(cliente.cpf),
                StringUtils.decodeDeBase64(cliente.telefone),
                StringUtils.decodeDeBase64(cliente.login),
                cliente.sexo,
                cliente.dataNascimento
            )

            addLinks(resposta)

            return resposta
        }

        private fun addLinks(resposta: ClienteDetalhesResponse) {
            resposta.add(
                linkTo(methodOn(ClienteController::class.java).alterarDados(null))
                    .withRel("atualizar")
            )
        }
    }

}
