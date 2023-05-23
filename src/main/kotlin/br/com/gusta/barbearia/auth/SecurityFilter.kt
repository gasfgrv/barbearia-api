package br.com.gusta.barbearia.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.util.Objects
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFilter(
    private val usuarioRepository: UsuarioRepository,
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val tokenJwt = recuperarToken(request)

        if (Objects.nonNull(tokenJwt)) {
            val subject = tokenService.getSubject(tokenJwt!!)
            val usuario = usuarioRepository.findById(subject).get()
            val authenticationToken = UsernamePasswordAuthenticationToken(usuario, null, usuario.authorities)
            SecurityContextHolder.getContext().authentication = authenticationToken
        }

        filterChain.doFilter(request, response)
    }

    private fun recuperarToken(request: HttpServletRequest): String? {
        var authorizationHeader = request.getHeader("Authorization")

        if (Objects.nonNull(authorizationHeader)) {
            authorizationHeader = authorizationHeader.replace("Bearer ", "")
        }

        return authorizationHeader
    }

}
