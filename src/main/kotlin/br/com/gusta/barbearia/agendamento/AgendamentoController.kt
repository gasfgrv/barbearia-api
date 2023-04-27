package br.com.gusta.barbearia.agendamento

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/agendamentos")
@Tag(name = "agendamentos")
class AgendamentoController @Autowired constructor(private val agendamentoService: AgendamentoService) {

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    fun consultarAgendamento(@PathVariable id: UUID?): Any {
        val agendamento = agendamentoService.consultarAgendamento(id!!)
        return ResponseEntity.ok().body(agendamento)
    }

    @PostMapping("/novo")
    @SecurityRequirement(name = "bearerAuth")
    fun novoAgendamento(@RequestBody @Valid form: NovoAgendamentoForm): ResponseEntity<NovoAgendamentoResponse> {
        if (agendamentoService.existeAgendamentoNoMesmoHorario(form.horario, form.barbeiro)) {
            throw RuntimeException("Já existe agendamento para esse horário!")
        }

        val agendamento = agendamentoService.novoAgendamento(form)

        val resposta = NovoAgendamentoResponse.paraResposta(agendamento).add(
            linkTo(methodOn(AgendamentoController::class.java).consultarAgendamento(agendamento.id)).withSelfRel()
        )

        val uri = resposta.getLink("self").get().toUri()

        return ResponseEntity.created(uri).body(resposta);
    }

}