package br.com.gusta.barbearia.auth

import org.springframework.stereotype.Service

@Service
class PerfilService(private val perfilRepository: PerfilRepository) {

    fun obterPerfil(tipoPerfil: String): Perfil {
        return perfilRepository.findByNome(tipoPerfil.uppercase())
    }

}
