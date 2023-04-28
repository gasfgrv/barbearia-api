package br.com.gusta.barbearia.agendamento

import br.com.gusta.barbearia.utils.StringUtils
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import java.net.URI
import java.time.OffsetDateTime
import org.springframework.hateoas.mediatype.problem.Problem
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@RestControllerAdvice
class AgendamentoControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(AgendamentoException::class)
    @ApiResponse(
        responseCode = "400",
        content = [Content(mediaType = "application/problem+json", schema = Schema(implementation = Problem::class))]
    )
    protected fun handleAgendamentoException(
        ex: AgendamentoException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val status = HttpStatus.BAD_REQUEST

        val headers = HttpHeaders()
        headers[HttpHeaders.CONTENT_TYPE] = mutableListOf(MediaType.APPLICATION_PROBLEM_JSON_VALUE)

        val extraProperties = HashMap<String, Any?>()
        extraProperties["statusAgendamento"] = StringUtils.capitalize(ex.statusAgendamento)

        return handleExceptionInternal(
            ex,
            criarProblema(ex, request, status, extraProperties),
            headers,
            status,
            request
        )
    }

    @ExceptionHandler(AgendamentoExistenteException::class)
    @ApiResponse(
        responseCode = "400",
        content = [Content(mediaType = "application/problem+json", schema = Schema(implementation = Problem::class))]
    )
    protected fun handleAgendamentoExistenteException(
        ex: AgendamentoExistenteException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val status = HttpStatus.BAD_REQUEST

        val headers = HttpHeaders()
        headers[HttpHeaders.CONTENT_TYPE] = mutableListOf(MediaType.APPLICATION_PROBLEM_JSON_VALUE)

        val extraProperties = HashMap<String, Any?>()
        extraProperties["horarioAgendado"] = ex.horario

        return handleExceptionInternal(
            ex,
            criarProblema(ex, request, status, extraProperties),
            headers,
            status,
            request
        )
    }

    @ExceptionHandler(AgendamentoNaoEncontradoException::class)
    @ApiResponse(
        responseCode = "404",
        content = [Content(mediaType = "application/problem+json", schema = Schema(implementation = Problem::class))]
    )
    protected fun handleAgendamentoNaoEncontradoException(
        ex: AgendamentoNaoEncontradoException,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val status = HttpStatus.NOT_FOUND

        val headers = HttpHeaders()
        headers[HttpHeaders.CONTENT_TYPE] = mutableListOf(MediaType.APPLICATION_PROBLEM_JSON_VALUE)

        return handleExceptionInternal(
            ex,
            criarProblema(ex, request, status),
            headers,
            status,
            request
        )
    }

    @ExceptionHandler(Exception::class)
    @ApiResponse(
        responseCode = "500",
        content = [Content(mediaType = "application/problem+json", schema = Schema(implementation = Problem::class))]
    )
    protected fun handleGenericException(
        ex: Exception,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val status = HttpStatus.INTERNAL_SERVER_ERROR

        val headers = HttpHeaders()
        headers[HttpHeaders.CONTENT_TYPE] = mutableListOf(MediaType.APPLICATION_PROBLEM_JSON_VALUE)

        val extraProperties = HashMap<String, Any?>()
        extraProperties["cause"] = ex.cause?.message

        return handleExceptionInternal(
            ex,
            criarProblema(ex, request, status, extraProperties),
            headers,
            status,
            request
        )
    }

    private fun criarProblema(
        ex: Exception,
        request: WebRequest,
        status: HttpStatus,
        extraProperties: MutableMap<String, Any?>? = null
    ): Problem {
        return Problem.create()
            .withTitle(StringUtils.converterParaSentenceCase(ex::class.simpleName!!))
            .withDetail(ex.message)
            .withInstance(URI((request as ServletWebRequest).request.requestURI.toString()))
            .withStatus(status)
            .withProperties { properties ->
                properties["timestamp"] = OffsetDateTime.now()
                extraProperties?.forEach { (key, value) -> properties[key] = value }
            }
    }

}