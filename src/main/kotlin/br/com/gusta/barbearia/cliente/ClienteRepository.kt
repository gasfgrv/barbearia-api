package br.com.gusta.barbearia.cliente

import java.util.Optional
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, UUID> {
    fun findByCpf(cpf: String): Optional<Cliente>
}
