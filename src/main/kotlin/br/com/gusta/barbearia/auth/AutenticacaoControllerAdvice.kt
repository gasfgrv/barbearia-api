package br.com.gusta.barbearia.auth

import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.net.URI
import org.springframework.hateoas.mediatype.problem.Problem
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class AutenticacaoControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(AuthenticationException::class)
    @ApiResponse(responseCode = "401", content = [
        Content(mediaType = "application/problem+json", schema = Schema(implementation = Problem::class))
    ])
    fun handleAuthenticationException(
            exception: AuthenticationException,
            request: WebRequest
    ): ResponseEntity<Any>? {
        val status = HttpStatus.UNAUTHORIZED

        val headers = HttpHeaders()
        headers[HttpHeaders.CONTENT_TYPE] = listOf(MediaType.APPLICATION_PROBLEM_JSON_VALUE)

        val problem = Problem.create()
                .withTitle("Erro ao autenticar usuário")
                .withDetail("Confira se as suas credenciais estão corretas")
                .withStatus(status)
                .withInstance(URI("/v1/login"))

        return handleExceptionInternal(exception, problem, headers, status, request)
    }

}
