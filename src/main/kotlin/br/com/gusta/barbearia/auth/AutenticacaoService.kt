package br.com.gusta.barbearia.auth

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AutenticacaoService(
    private val authencationManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    fun autenticarUsuario(dadosAutenticacao: DadosAutenticacao): String {
        val authencationToken = UsernamePasswordAuthenticationToken(dadosAutenticacao.login, dadosAutenticacao.senha)
        val authentication = authencationManager.authenticate(authencationToken)
        return tokenService.gerarToken(authentication.principal as Usuario)
    }

    fun getUsuarioAutenticado(): Usuario = SecurityContextHolder.getContext().authentication.principal as Usuario

}
