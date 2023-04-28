package br.com.gusta.barbearia.agendamento

class AgendamentoExistenteException(val horario: String) :
    RuntimeException("Já existe agendamento para esse horário com o profissional")