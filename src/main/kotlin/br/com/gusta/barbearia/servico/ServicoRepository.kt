package br.com.gusta.barbearia.servico

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ServicoRepository : JpaRepository<Servico, Long> {

    private companion object {
        const val SELECT_SERVICOS = """
            SELECT
              servico.id, servico.nome, servico.descricao, servico.preco, servico.duracacao, servico.ativo
            FROM
              public.servico
            WHERE
              servico.ativo = TRUE"""

        const val SELECT_SERVICO = """
            SELECT
              servico.id, servico.nome, servico.descricao, servico.preco, servico.duracacao, servico.ativo
            FROM
              public.servico
            WHERE
              servico.ativo = TRUE
              AND servico.id = :id"""
    }

    @Query(value = SELECT_SERVICOS, nativeQuery = true)
    fun listarServicosAtivos(): List<Servico>

    @Query(value = SELECT_SERVICO, nativeQuery = true)
    fun listarServicoAtivoPorId(@Param("id") id: Long): Optional<Servico>

}