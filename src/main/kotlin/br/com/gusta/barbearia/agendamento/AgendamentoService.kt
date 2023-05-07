package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.barbeiro.BarbeiroService
import br.com.gusta.barbearia.cliente.ClienteService
import br.com.gusta.barbearia.servico.ServicoService
import br.com.gusta.barbearia.utils.DataHoraUtils
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AgendamentoService @Autowired constructor(
    private val agendamentoRepository: AgendamentoRepository,
    private val clienteService: ClienteService,
    private val servicoService: ServicoService,
    private val barbeiroService: BarbeiroService,
) {

    @Transactional
    fun novoAgendamento(novoAgendamento: NovoAgendamentoForm): Agendamento {
        if (verificaSePodeAgendar(novoAgendamento)) {
            throw AgendamentoExistenteException(DataHoraUtils.formatar(novoAgendamento.horario))
        }

        val agendamento = Agendamento(
            cliente = clienteService.buscarCliente(novoAgendamento.cliente),
            barbeiro = barbeiroService.buscarBarbeiro(novoAgendamento.barbeiro),
            horario = novoAgendamento.horario
        )

        agendamento.adicionarServicos(
            novoAgendamento.servicos.map(servicoService::selecionarServico)
        )
        agendamento.agendar()

        return agendamentoRepository.save(agendamento)
    }

    fun consultarDadosAgendamento(id: UUID): Agendamento {
        return agendamentoRepository.findById(id)
            .orElseThrow { AgendamentoNaoEncontradoException() }
    }

    fun cancelarAgendamento(id: UUID) {
        agendamentoRepository.findById(id)
            .ifPresent { agendamento ->
                agendamento.cancelarAgendamento()
                agendamentoRepository.save(agendamento)
            }
    }

    fun concluirAgendamento(id: UUID) {
        agendamentoRepository.findById(id)
            .ifPresent { agendamento ->
                agendamento.concluirAgendamento()
                agendamentoRepository.save(agendamento)
            }
    }

    //    alterarAgendamento
//    listarAgendamentos
    private fun existeAgendamentoParaOMesmoHorario(horario: LocalDateTime, barbeiro: UUID): Boolean =
        agendamentoRepository.existsAgendamentoByHorarioAndBarbeiroId(horario, barbeiro)

    private fun verificaSePodeAgendar(novoAgendamento: NovoAgendamentoForm): Boolean =
        existeAgendamentoParaOMesmoHorario(novoAgendamento.horario, novoAgendamento.barbeiro) ||
        barbeiroService.verificaSeBarbeiroVaiEstarOcupadoNoHorario(novoAgendamento.horario, novoAgendamento.barbeiro)
}
