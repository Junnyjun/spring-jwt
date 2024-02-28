package git.io.kotlinjwt.security.application.usecase

import git.io.kotlinjwt.security.user.gateway.out.repository.User.EncryptAlgorithm.BCRYPT
import git.io.kotlinjwt.security.user.gateway.out.repository.User.EncryptAlgorithm.SCRYPT
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomAuthenticationProvider(
    private val userDetailService: CustomUserDetailService,
) : AuthenticationProvider {
    private val sCryptPasswordEncoder: SCryptPasswordEncoder = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()
    private val bCryptPasswordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun authenticate(authentication: Authentication): Authentication {
        val name = authentication.name
        val password = authentication.credentials.toString()
        val user: CustomUserDetails = userDetailService.loadUserByUsername(name)

        return when (user.getUser.algorithm) {
            BCRYPT -> {
                bCryptPasswordEncoder.matches(password, user.password)
            }
            SCRYPT -> {
                sCryptPasswordEncoder.matches(password, user.password)
            }
        }
            .takeIf { it }
            ?.let { return UsernamePasswordAuthenticationToken(user.username, user.password, user.authorities) }
            ?: throw BadCredentialsException("Bad credentials")
    }

    override fun supports(authentication: Class<*>): Boolean = UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
}