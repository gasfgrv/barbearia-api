package br.com.gusta.barbearia.email

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val javaMailSender: JavaMailSender) {

    fun enviarEmail(assunto: String, texto: String, email: String) {
        val message = SimpleMailMessage()
        message.subject = assunto
        message.text = texto
        message.setTo(email)
        javaMailSender.send(message)
    }

}
