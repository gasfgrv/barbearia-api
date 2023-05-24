package br.com.gusta.barbearia.servico


import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ServicoRepository : JpaRepository<Servico, Long> {

    @Query(
        value = "select servico.id, servico.nome, servico.descricao, servico.preco, servico.duracacao, servico.ativo from public.servico where servico.ativo = true",
        nativeQuery = true
    )
    fun listarServicosAtivos(): List<Servico>

    @Query(
        value = "select servico.id, servico.nome, servico.descricao, servico.preco, servico.duracacao, servico.ativo from public.servico where servico.ativo = true AND servico.id = :id",
        nativeQuery = true
    )
    fun listarServicoAtivoPorId(@Param("id") id: Long): Optional<Servico>

}
