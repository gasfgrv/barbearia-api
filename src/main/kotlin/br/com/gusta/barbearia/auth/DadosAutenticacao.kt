package br.com.gusta.barbearia.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class DadosAutenticacao(
    @Email
    val login: String,
    @NotBlank
    val senha: String
)
