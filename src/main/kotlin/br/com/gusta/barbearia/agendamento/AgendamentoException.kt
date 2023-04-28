package br.com.gusta.barbearia.agendamento

class AgendamentoException(override val message: String, val statusAgendamento: String) : RuntimeException(message)
