package br.com.gusta.barbearia.servico

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import java.util.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/servicos")
@Tag(name = "Serviços")
class ServicoController(private val servicoService: ServicoService) {

    @GetMapping
    fun listarServicos(): ResponseEntity<List<ServicosResponse>> {
        val servicos = servicoService.listarServicos()
            .map { servico -> ServicosResponse.paraResposta(servico) }

        return ResponseEntity.ok(servicos)
    }

    @GetMapping("/{id}")
    fun detalharServico(@PathVariable id: Long): ResponseEntity<ServicoResponse> {
        val servico = servicoService.selecionarServico(id)
        val resposta = ServicoResponse.paraResposta(servico)
        return ResponseEntity.ok(resposta)
    }

    @PostMapping("/novo")
    fun novoServico(@RequestBody @Valid novoServico: NovoServicoForm): ResponseEntity<ServicoResponse> {
        val servico = ServicoResponse.paraResposta(servicoService.novoServico(novoServico))
        val location = servico.getLink("self").get().toUri()
        return ResponseEntity.created(location).body(servico)
    }


    @PutMapping("/alterar")
    fun alterarServico(@RequestBody @Valid alterarServico: AlterarServicoForm?): ResponseEntity<ServicoResponse> {
        val servico = servicoService.alterarServico(alterarServico!!)
        return ResponseEntity.ok(ServicoResponse.paraResposta(servico))
    }

    @DeleteMapping("/{id}/desativar")
    fun desativarServico(@PathVariable id: Long): ResponseEntity<Unit> {
        servicoService.desativarServico(id)
        return ResponseEntity.noContent().build()
    }

}
