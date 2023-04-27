package br.com.gusta.barbearia.barbeiro

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface BarbeiroRepository : JpaRepository<Barbeiro, UUID> {
}