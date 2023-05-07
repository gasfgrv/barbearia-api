package br.com.gusta.barbearia.barbeiro

import br.com.gusta.barbearia.agendamento.Agendamento
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
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
    val telefone: String,
    @OneToMany(mappedBy = "barbeiro", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val agendamentos: List<Agendamento>
)
