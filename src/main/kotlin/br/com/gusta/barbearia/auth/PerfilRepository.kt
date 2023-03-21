package br.com.gusta.barbearia.auth

import org.springframework.data.jpa.repository.JpaRepository

interface PerfilRepository : JpaRepository<Perfil, Long>