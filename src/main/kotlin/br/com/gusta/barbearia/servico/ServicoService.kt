package br.com.gusta.barbearia.servico

import org.springframework.stereotype.Service

@Service
class ServicoService(private val servicoRepository: ServicoRepository) {

    //    Novo serviço
//    Listar servicos
    fun listarServicos(): List<Servico> {
        return servicoRepository.listarServicosAtivos()
    }
//    Seleciona servico
    fun selecionarServico(id: Long): Servico {
        return servicoRepository.listarServicoAtivoPorId(id)
                .orElseThrow { RuntimeException() }
    }
//    alterar servico
//    desativar servico

}