package br.com.gusta.barbearia.auth

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "usuario")
data class Usuario(
    @Id
    private val login: String,
    private val senha: String,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "perfil", referencedColumnName = "id")
    private val perfil: Perfil
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(perfil)

    override fun getPassword(): String = senha

    override fun getUsername(): String = login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}
