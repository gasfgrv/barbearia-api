package br.com.gusta.barbearia.servico

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.SEQUENCE
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "servico")
data class Servico(
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "servicos_generator")
    @SequenceGenerator(name = "servicos_generator", sequenceName = "servicos_sequence", allocationSize = 1)
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "nome")
    var nome: String,

    @Column(name = "descricao")
    var descricao: String,

    @Column(name = "preco")
    var preco: BigDecimal,

    @Column(name = "duracacao")
    var duracacao: Int,

    @Column(name = "ativo")
    var ativo: Boolean = false
) {

    fun ativar() {
        this.ativo = true
    }

    fun desativar() {
        this.ativo = false
    }

}
