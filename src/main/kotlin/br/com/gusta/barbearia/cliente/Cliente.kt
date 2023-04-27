package br.com.gusta.barbearia.cliente

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "cliente")
data class Cliente(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.UUID)
        var id: UUID? = null,

        @Column(name = "nome")
        val nome: String,

        @Column(name = "cpf")
        val cpf: String,

        @Column(name = "telefone")
        val telefone: String,

        @Column(name = "login")
        val login: String,

        @Column(name = "sexo")
        val sexo: String,

        @Column(name = "data_nascimento")
        val dataNascimento: LocalDate
)
