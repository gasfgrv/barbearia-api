package br.com.gusta.barbearia.cliente

class ClienteExistenteException :
    RuntimeException("Esse cliente já existe e não pode ser cadastrado novamente")
