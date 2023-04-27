package br.com.gusta.barbearia.cliente

import java.util.*
import org.springframework.stereotype.Service

@Service
class ClienteService(private val clienteRepository: ClienteRepository) {

    fun buscarCliente(id: UUID): Cliente {
        return clienteRepository.findById(id)
                .orElseThrow { RuntimeException() }
    }

}
