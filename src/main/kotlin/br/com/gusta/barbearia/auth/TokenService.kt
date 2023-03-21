package br.com.gusta.barbearia.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TokenService(
    @Value("\${api.security.token.secret}")
    private val secret: String
) {

    fun gerarToken(usuario: Usuario): String? {
        return JWT.create()
            .withIssuer("barbearia")
            .withSubject(usuario.username)
            .withExpiresAt(getExpiracao())
            .sign(getAlgoritmo(secret))
    }

    fun getSubject(token: String): String? {
        return JWT.require(getAlgoritmo(secret))
            .withIssuer("barbearia")
            .build()
            .verify(token)
            .subject
    }

    private fun getAlgoritmo(secret: String): Algorithm? {
        return Algorithm.HMAC256(secret);
    }

    private fun getExpiracao(): Instant {
        return OffsetDateTime.now().plusHours(1).toInstant()
    }

}