package git.io.kotlinjwt.security.application.usecase

import git.io.kotlinjwt.security.user.gateway.out.repository.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    val user: User
): UserDetails {
    override fun getAuthorities(): List<GrantedAuthority> = user.authorities
        .map { authority -> GrantedAuthority { authority.name } }
        .toList()

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}