package br.com.gusta.barbearia.auth

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/login")
@Tag(name = "Login")
class AutenticacaoController(
        private val authencationManager: AuthenticationManager,
        private val tokenService: TokenService
) {

    @PostMapping
    fun login(@RequestBody @Valid dadosAutenticacao: DadosAutenticacao): ResponseEntity<DadosTokenJWT> {
        val authencationToken = UsernamePasswordAuthenticationToken(dadosAutenticacao.login, dadosAutenticacao.senha)
        val authentication = authencationManager.authenticate(authencationToken)
        val tokenJWT = tokenService.gerarToken(authentication.principal as Usuario)
        return ResponseEntity.ok(DadosTokenJWT(tokenJWT!!))
    }

}