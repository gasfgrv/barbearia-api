package br.com.gusta.barbearia.barbeiro

import java.util.UUID
import org.springframework.stereotype.Service

@Service
class BarbeiroService(private val barbeiroRepository: BarbeiroRepository) {

    fun buscarBarbeiro(id: UUID): Barbeiro {
        return barbeiroRepository.findById(id)
                .orElseThrow { RuntimeException() }
    }

}
