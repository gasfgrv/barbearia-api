package br.com.gusta.barbearia.cliente

import java.util.*
import org.springframework.data.jpa.repository.JpaRepository

interface ClienteRepository : JpaRepository<Cliente, UUID>