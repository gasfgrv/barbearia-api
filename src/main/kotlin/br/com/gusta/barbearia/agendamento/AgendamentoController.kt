package br.com.gusta.barbearia.agendamento

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/agendamentos")
@Tag(name = "agendamentos")
class AgendamentoController @Autowired constructor(private val agendamentoService: AgendamentoService) {

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    fun consultarAgendamento(@PathVariable id: UUID): ResponseEntity<AgendamentoDetalhesResponse> {
        return ResponseEntity.ok()
                .body(AgendamentoDetalhesResponse.paraResposta(agendamentoService.consultarDadosAgendamento(id)))
    }

    @GetMapping("/{id}/cancelar")
    @SecurityRequirement(name = "bearerAuth")
    fun cancelarAgendamento(@PathVariable id: UUID): ResponseEntity<Unit> {
        agendamentoService.cancelarAgendamento(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}/concluir")
    @SecurityRequirement(name = "bearerAuth")
    fun concluirAgendamento(@PathVariable id: UUID): ResponseEntity<Unit> {
        agendamentoService.concluirAgendamento(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/novo")
    @SecurityRequirement(name = "bearerAuth")
    fun novoAgendamento(@RequestBody @Valid form: NovoAgendamentoForm): ResponseEntity<AgendamentoResponse> {
        val agendamento = AgendamentoResponse.paraResposta(agendamentoService.novoAgendamento(form))
        return ResponseEntity
                .created(agendamento.getLink("self").get().toUri())
                .body(agendamento)
    }

    @PutMapping("/remarcar")
    @SecurityRequirement(name = "bearerAuth")
    fun alterarAgendamento(@RequestBody @Valid form: AlterarAgendamentoForm): ResponseEntity<AgendamentoResponse> {
        val agendamento = AgendamentoResponse.paraResposta(agendamentoService.alterarAgendamento(form))
        return ResponseEntity.ok(agendamento)
    }

}