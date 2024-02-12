package git.io.kotlinjwt.security.application.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class Users(
    val username: String,
    val password: String,
    val authority: List<String>
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> = authority.map { GrantedAuthority { it } }
    override fun getPassword(): String = password
    override fun getUsername(): String = username
    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
