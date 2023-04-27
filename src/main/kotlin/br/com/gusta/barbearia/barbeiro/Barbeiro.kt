package br.com.gusta.barbearia.barbeiro

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "barbeiro")
data class Barbeiro(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.UUID)
        var id: UUID? = null,
        val nome: String,
        val login: String,
        val telefone: String
)
