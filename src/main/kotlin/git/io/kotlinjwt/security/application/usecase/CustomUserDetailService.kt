package git.io.kotlinjwt.security.application.usecase

import git.io.kotlinjwt.security.user.gateway.out.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails = userRepository.findByUsername(username)
            ?.let { CustomUserDetails(it) }
            ?: throw UsernameNotFoundException("User not found")
}