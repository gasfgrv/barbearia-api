package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.barbeiro.BarbeiroService
import br.com.gusta.barbearia.cliente.ClienteService
import br.com.gusta.barbearia.servico.ServicoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AgendamentoService @Autowired constructor(
    private val agendamentoRepository: AgendamentoRepository,
    private val clienteService: ClienteService,
    private val servicoService: ServicoService,
    private val barbeiroService: BarbeiroService,
) {

    @Transactional
    fun novoAgendamento(novoAgendamento: NovoAgendamentoForm): Agendamento {
        val cliente = clienteService.buscarCliente(novoAgendamento.cliente)
        val barbeiro = barbeiroService.buscarBarbeiro(novoAgendamento.barbeiro)
        val servicos = novoAgendamento.servicos.map(servicoService::selecionarServico)

        val agendamento = Agendamento(
            cliente = cliente,
            barbeiro = barbeiro,
            horario = novoAgendamento.horario
        )

        agendamento.adicionarServicos(servicos)
        agendamento.agendar()

        return agendamentoRepository.save(agendamento)
    }

    fun consultarAgendamento(id: UUID): Any {
        return agendamentoRepository.findById(id).orElseThrow { RuntimeException() }
    }
//    concluirAgendamento
//    cancelarAgendamento
//    alterarAgendamento
//    listarAgendamentos
}