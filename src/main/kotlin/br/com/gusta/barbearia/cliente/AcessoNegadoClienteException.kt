package br.com.gusta.barbearia.cliente

class AcessoNegadoClienteException :
    RuntimeException("Você não pode acessar os dados desse cliente")
