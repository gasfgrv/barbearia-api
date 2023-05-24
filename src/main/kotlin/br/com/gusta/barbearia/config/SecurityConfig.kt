package br.com.gusta.barbearia.config

import br.com.gusta.barbearia.auth.SecurityFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(private val securityFilter: SecurityFilter) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .csrf { csrf -> csrf.disable() }
        .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .authorizeHttpRequests { requests ->
            requests.requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
            requests.requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
            requests.requestMatchers(HttpMethod.POST, "/v1/login").permitAll()
            requests.requestMatchers(HttpMethod.POST, "/v1/agendamentos/novo").hasAuthority("CLIENTE")
            requests.requestMatchers(HttpMethod.PUT, "/v1/agendamentos/remarcar").hasAuthority("CLIENTE")
            requests.requestMatchers(HttpMethod.GET, "/v1/agendamentos/{id}").hasAnyAuthority("CLIENTE", "BARBEIRO")
            requests.requestMatchers(HttpMethod.GET, "/v1/agendamentos/{id}/concluir").hasAuthority("BARBEIRO")
            requests.requestMatchers(HttpMethod.GET, "/v1/agendamentos/{id}/cancelar").hasAuthority("CLIENTE")
            requests.requestMatchers(HttpMethod.POST, "/v1/servicos/novo").hasAnyAuthority("BARBEIRO", "ADMIN")
            requests.requestMatchers(HttpMethod.PUT, "/v1/servicos/alterar").hasAnyAuthority("BARBEIRO", "ADMIN")
            requests.requestMatchers(HttpMethod.DELETE, "/v1/servicos/{id}/desativar").hasAnyAuthority("BARBEIRO", "ADMIN")
            requests.requestMatchers(HttpMethod.POST, "/v1/clientes/novo").permitAll()
            requests.requestMatchers(HttpMethod.POST, "/v1/barbeiros/novo").permitAll()
            requests.anyRequest().authenticated()
        }
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
        .build()

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
        configuration.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}