package br.com.gusta.barbearia.cliente

import br.com.gusta.barbearia.utils.StringUtils
import java.time.LocalDate
import org.springframework.hateoas.RepresentationModel

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

            return ClienteDetalhesResponse(
                StringUtils.decodeDeBase64(cliente.nome),
                StringUtils.decodeDeBase64(cliente.cpf),
                StringUtils.decodeDeBase64(cliente.telefone),
                cliente.login,
                cliente.sexo,
                cliente.dataNascimento
            )
        }

    }

}
