package br.com.gusta.barbearia.utils

import java.util.Objects
import java.util.regex.Pattern

class StringUtils {
    companion object {
        fun capitalize(texto: String): String = texto
            .lowercase()
            .replaceFirstChar { it.uppercase() }

        fun converterParaSentenceCase(camelCaseString: String): String {
            if (camelCaseString.isEmpty() || Objects.isNull(camelCaseString)) {
                return camelCaseString
            }

            val result = Pattern.compile("([A-Z])")
                .matcher(camelCaseString)
                .replaceAll(" $1")
                .trim()

            return result.substring(0, 1).uppercase() + result.substring(1).lowercase();
        }
    }
}