package git.io.kotlinjwt.security.application.usecase

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class InMemoryUserDetailsService(
    private val users: List<UserDetails>
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails = users.find { it.username == username } ?: throw IllegalArgumentException("User not found")
}