package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.barbeiro.Barbeiro
import br.com.gusta.barbearia.cliente.Cliente
import br.com.gusta.barbearia.servico.Servico
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

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
            throw RuntimeException()
        }

        this.status = Status.CANCELADO
    }

    fun concluirAgendamento() {
        if (Status.AGENDADO != this.status) {
            throw RuntimeException()
        }

        this.status = Status.CONCLUIDO
    }

}
