package br.com.gusta.barbearia.servico

import java.math.BigDecimal

data class ServicoResponse(
        val nome: String,
        val descricao: String,
        val preco: BigDecimal,
        val duracacao: Int
) {

    companion object Mapper {
        fun paraResposta(servico: Servico): ServicoResponse {
            return ServicoResponse(
                    servico.nome,
                    servico.descricao,
                    servico.preco,
                    servico.duracacao
            )
        }
    }

}
