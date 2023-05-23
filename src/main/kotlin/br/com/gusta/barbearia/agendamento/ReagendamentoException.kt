package br.com.gusta.barbearia.agendamento

class ReagendamentoException :
    RuntimeException("Não é possível reagendar um agendamento já cancelado ou concluído")
