package br.com.gusta.barbearia.cliente

import br.com.gusta.barbearia.auth.AutenticacaoService
import br.com.gusta.barbearia.auth.PerfilService
import br.com.gusta.barbearia.auth.Usuario
import br.com.gusta.barbearia.auth.UsuarioService
import br.com.gusta.barbearia.email.EmailService
import br.com.gusta.barbearia.utils.StringUtils
import java.util.UUID
import kotlin.concurrent.thread
import org.springframework.stereotype.Service

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository,
    private val perfilService: PerfilService,
    private val usuarioService: UsuarioService,
    private val emailService: EmailService,
    private val autenticacaoService: AutenticacaoService
) {

    fun novoCliente(novoClienteForm: NovoClienteForm): Cliente {
        val regex = Regex("[.\\-\\s]")
        val cpf = novoClienteForm.cpf.replace(regex, "")
        val clienteNaBaseDeDados = clienteRepository.findByCpf(StringUtils.encodeParaBase64(cpf))

        if (clienteNaBaseDeDados.isPresent) {
            throw ClienteExistenteException()
        }

        val novoUsuario = Usuario(
            novoClienteForm.login,
            novoClienteForm.senha,
            perfilService.obterPerfil("cliente")
        )

        val usuario = usuarioService.novoUsuario(novoUsuario)

        val cliente = Cliente(
            nome = StringUtils.encodeParaBase64(novoClienteForm.nome),
            cpf = StringUtils.encodeParaBase64(cpf),
            sexo = novoClienteForm.sexo.uppercase(),
            telefone = StringUtils.encodeParaBase64(novoClienteForm.telefone),
            dataNascimento = novoClienteForm.dataNascimento,
            login = StringUtils.decodeDeBase64(usuario.username)
        )

        val clienteSalvo = clienteRepository.save(cliente)

        thread(start = true, isDaemon = false, name = "enviarEmailThread") {
            emailService.enviarEmail(
                "Cadastro de Cliente",
                "Parabens, seu cadastro foi concluido",
                StringUtils.decodeDeBase64(cliente.login)
            )
        }

        return clienteSalvo
    }

    fun buscarCliente(id: UUID): Cliente {
        val usuarioLogado = autenticacaoService.getUsuarioAutenticado()
        val cliente = clienteRepository.findById(id).orElseThrow { ClienteNaoEncontradoException() }

        if (usuarioLogado.username != cliente.login) {
            throw AcessoNegadoClienteException()
        }

        return cliente
    }

    fun atualizarDadosCliente(atualizarDadosForm: AtualizarDadosForm): Cliente {
        val clienteNaBaseDeDados = clienteRepository.findById(atualizarDadosForm.id)

        if (clienteNaBaseDeDados.isEmpty) {
            throw ClienteNaoEncontradoException()
        }

        val clienteAtualizado = clienteNaBaseDeDados.get()
        clienteAtualizado.nome = StringUtils.encodeParaBase64(atualizarDadosForm.nome)
        clienteAtualizado.telefone = StringUtils.encodeParaBase64(atualizarDadosForm.telefone)

        return clienteRepository.save(clienteAtualizado)
    }

}