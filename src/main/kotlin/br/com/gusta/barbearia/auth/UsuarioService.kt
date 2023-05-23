package br.com.gusta.barbearia.auth

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return usuarioRepository.findById(username!!)
            .orElseThrow { throw UsernameNotFoundException("Usuario não encontrado") }
    }

}
