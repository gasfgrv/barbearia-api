package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.barbeiro.Barbeiro
import br.com.gusta.barbearia.cliente.Cliente
import br.com.gusta.barbearia.servico.Servico
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "agendamento")
data class Agendamento(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    val cliente: Cliente,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "agendamento_servico",
        joinColumns = [JoinColumn(name = "agendamento")],
        inverseJoinColumns = [JoinColumn(name = "servico")]
    )
    private val servicos: MutableList<Servico> = ArrayList(),

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "barbeiro", referencedColumnName = "id")
    val barbeiro: Barbeiro,

    @Column(name = "horario")
    val horario: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: Status? = null
) {
    fun adicionarServicos(servicos: List<Servico>) {
        servicos.forEach { servico -> this.servicos.add(servico) }
    }

    fun obterServicos(): List<Servico> {
        return servicos.toList()
    }

    fun agendar() {
        this.status = Status.AGENDADO
    }

    fun cancelarAgendamento() {
        if (Status.AGENDADO != this.status) {
            throw AgendamentoException(
                "Não se pode cancelar um agendamento que já esteja concluído ou cancelado",
                this.status!!.name
            )
        }

        this.status = Status.CANCELADO
    }

    fun concluirAgendamento() {
        if (Status.AGENDADO != this.status) {
            throw AgendamentoException(
                "Não se pode concluir um agendamento que já esteja concluído ou cancelado",
                this.status!!.name
            )
        }

        this.status = Status.CONCLUIDO
    }

}
