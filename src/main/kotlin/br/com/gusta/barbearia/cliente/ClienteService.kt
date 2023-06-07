package br.com.gusta.barbearia.cliente

import br.com.gusta.barbearia.auth.PerfilService
import br.com.gusta.barbearia.auth.Usuario
import br.com.gusta.barbearia.auth.UsuarioService
import br.com.gusta.barbearia.utils.StringUtils
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class ClienteService(
    private val clienteRepository: ClienteRepository,
    private val perfilService: PerfilService,
    private val usuarioService: UsuarioService
) {
    fun novoCliente(novoClienteForm: NovoClienteForm): Cliente {
        val clienteNaBaseDeDados = clienteRepository.findByCpf(novoClienteForm.cpf)

        if (clienteNaBaseDeDados.isPresent) {
            throw ClienteExistenteException()
        }

        val novoUsuario = Usuario(
            novoClienteForm.login,
            novoClienteForm.senha,
            perfilService.obterPerfil("cliente")
        )

        val usuario = usuarioService.novoUsuario(novoUsuario)

        val regex = Regex("[.\\-\\s]")
        val cpf = novoClienteForm.cpf.replace(regex, "")

        val cliente = Cliente(
            nome = StringUtils.encodeParaBase64(novoClienteForm.nome),
            cpf = StringUtils.encodeParaBase64(cpf),
            sexo = novoClienteForm.sexo.uppercase(),
            telefone = StringUtils.encodeParaBase64(novoClienteForm.telefone),
            dataNascimento = novoClienteForm.dataNascimento,
            login = usuario.username // TODO: colocar em base64 
        )

        return clienteRepository.save(cliente)
    }

    fun buscarCliente(id: UUID): Cliente {
        return clienteRepository.findById(id)
            .orElseThrow { RuntimeException() }
    }

}
