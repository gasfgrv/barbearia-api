package br.com.gusta.barbearia.servico

data class ServicosResponse(
    val nome: String,
    val descricao: String
) {

    companion object Mapper {
        fun paraResposta(servico: Servico): ServicosResponse {
            return ServicosResponse(
                servico.nome,
                servico.descricao
            )
        }
    }

}
