package br.com.gusta.barbearia.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataHoraUtils {

    companion object {
        fun formatar(horario: LocalDateTime): String {
            return horario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
        }
    }

}