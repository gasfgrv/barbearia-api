package br.com.gusta.barbearia.servico

import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class ServicoService(private val servicoRepository: ServicoRepository) {

    fun novoServico(novoServico: NovoServicoForm): Servico {
        val servico = Servico(
            nome = novoServico.nome,
            descricao = novoServico.descricao,
            preco = BigDecimal(novoServico.preco.replace(",", ".")),
            duracacao = novoServico.duracao
        )

        servico.ativar()

        return servicoRepository.save(servico)
    }

    fun listarServicos(): List<Servico> {
        return servicoRepository.listarServicosAtivos()
    }

    fun selecionarServico(id: Long): Servico {
        return servicoRepository.listarServicoAtivoPorId(id)
            .orElseThrow { ServicoNaoEncontradoException() }
    }

    fun alterarServico(alterarServico: AlterarServicoForm): Servico {
        val servico = servicoRepository.listarServicoAtivoPorId(alterarServico.id)
            .orElseThrow { ServicoNaoEncontradoException() }

        servico.nome = alterarServico.nome
        servico.descricao = alterarServico.descricao
        servico.preco = BigDecimal(alterarServico.preco.replace(",", "."))
        servico.duracacao = alterarServico.duracao

        return servicoRepository.save(servico)
    }

    fun desativarServico(id: Long) {
        servicoRepository.listarServicoAtivoPorId(id)
            .ifPresent { servico ->
                servico.desativar()
                servicoRepository.save(servico)
            }
    }

}