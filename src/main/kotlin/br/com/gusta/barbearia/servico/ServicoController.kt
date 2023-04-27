package br.com.gusta.barbearia.servico

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

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
    fun DetalharServico(@PathVariable id: Long): ResponseEntity<ServicoResponse> {
        val servico = servicoService.selecionarServico(id)
        val resposta = ServicoResponse.paraResposta(servico)
        return ResponseEntity.ok(resposta)
    }

}