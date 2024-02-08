package git.io.kotlinjwt.security.config

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


interface DummyUser : UserDetails {
    override fun getAuthorities(): Collection<out GrantedAuthority> = listOf("READ", "WRITE").map { GrantedAuthority { it } }
    override fun getPassword(): String = "1234"
    override fun getUsername(): String = "junny"
}