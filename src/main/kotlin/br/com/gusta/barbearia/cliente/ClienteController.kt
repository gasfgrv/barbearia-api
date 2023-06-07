package br.com.gusta.barbearia.cliente

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/clientes")
@Tag(name = "Clientes")
class ClienteController(
    private val clienteService: ClienteService
) {

    @PostMapping("/novo")
    fun novoCliente(@RequestBody @Valid novoCLienteForm: NovoClienteForm): ResponseEntity<ClienteResponse> {
        val cliente = clienteService.novoCliente(novoCLienteForm)
        val resposta = ClienteResponse.paraResposta(cliente)
        val locationHeader = resposta.getLink("self").get().toUri()
        return ResponseEntity.created(locationHeader).body(resposta)
    }

    @PutMapping("/atualizar")
    fun alterarDados(@RequestBody @Valid atualizarDadosForm: AtualizarDadosForm?): ResponseEntity<ClienteDetalhesResponse> {
        val cliente = clienteService.atualizarDadosCliente(atualizarDadosForm!!)
        val resposta = ClienteDetalhesResponse.paraResposta(cliente)
        return ResponseEntity.ok(resposta)
    }

    @GetMapping("/{id}/detalhes")
    @SecurityRequirement(name = "bearerAuth")
    fun detalharCliente(@PathVariable id: UUID): ResponseEntity<ClienteDetalhesResponse> {
        val cliente = clienteService.buscarCliente(id)
        val resposta = ClienteDetalhesResponse.paraResposta(cliente)
        return ResponseEntity.ok(resposta)
    }

}