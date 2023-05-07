package br.com.gusta.barbearia.auth

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/login")
@Tag(name = "Login")
class AutenticacaoController(private val autenticacaoService: AutenticacaoService) {

    @PostMapping
    fun login(@RequestBody @Valid dadosAutenticacao: DadosAutenticacao): ResponseEntity<TokenJwtResponse> {
        val tokenJWT = autenticacaoService.autenticarUsuario(dadosAutenticacao)
        return ResponseEntity.ok(TokenJwtResponse(tokenJWT))
    }

}