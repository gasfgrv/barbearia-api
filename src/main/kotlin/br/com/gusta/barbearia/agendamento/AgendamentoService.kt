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
        if (verificaSePodeAgendar(novoAgendamento.horario, novoAgendamento.barbeiro)) {
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

    fun alterarAgendamento(alterarAgendamento: AlterarAgendamentoForm): Agendamento {
        val agendamento = agendamentoRepository.findById(alterarAgendamento.agendamento)
                .orElseThrow(::AgendamentoNaoEncontradoException)
        agendamento.horario = alterarAgendamento.novoHorario

        if (Status.AGENDADO != agendamento.status) {
            throw ReagendamentoException()
        }

        if (verificaSePodeAgendar(agendamento.horario, agendamento.barbeiro.id!!)) {
            throw AgendamentoExistenteException(DataHoraUtils.formatar(agendamento.horario))
        }

        return agendamentoRepository.save(agendamento)
    }

    private fun existeAgendamentoParaOMesmoHorario(horario: LocalDateTime, barbeiro: UUID): Boolean {
        return agendamentoRepository.existsAgendamentoByHorarioAndBarbeiroId(horario, barbeiro)
    }

    private fun verificaSePodeAgendar(horario: LocalDateTime, barbeiroId: UUID): Boolean {
        return existeAgendamentoParaOMesmoHorario(horario, barbeiroId) ||
                barbeiroService.verificaSeBarbeiroVaiEstarOcupadoNoHorario(horario, barbeiroId)
    }
}
