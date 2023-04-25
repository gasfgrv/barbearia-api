package br.com.gusta.barbearia.servico

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "servico")
data class Servico(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val nome: String,
        val descricao: String,
        val preco: BigDecimal,
        val duracacao: Int,
        val ativo: Boolean
)
