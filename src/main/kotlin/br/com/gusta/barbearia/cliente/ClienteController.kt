package br.com.gusta.barbearia.cliente

import br.com.gusta.barbearia.auth.Usuario
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/clientes")
@Tag(name = "Clientes")
class ClienteController(private val clienteService: ClienteService) {

    @PostMapping("/novo")
    fun novoCliente(@RequestBody @Valid novoCLienteForm: NovoClienteForm): ResponseEntity<ClienteResponse> {
        val cliente = ClienteResponse.paraResposta(clienteService.novoCliente(novoCLienteForm))
        return ResponseEntity
            .created(cliente.getLink("self").get().toUri())
            .body(cliente)
    }

    @GetMapping("/{id}/detalhes")
    @SecurityRequirement(name = "bearerAuth")
    fun detalharCliente(@PathVariable id: UUID): ResponseEntity<ClienteDetalhesResponse> {
//        val usuarioLogado = SecurityContextHolder.getContext().authentication.principal as Usuario
        val cliente = clienteService.buscarCliente(id)


//        if (usuarioLogado.username != cliente.login) {
//            throw AcessoNegadoClienteException()
//        }

        val detalhesCliente = ClienteDetalhesResponse.paraResposta(cliente)
        return ResponseEntity.ok(detalhesCliente)
    }

}