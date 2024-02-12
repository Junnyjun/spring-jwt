package git.io.kotlinjwt.security.config

import git.io.kotlinjwt.security.application.domain.Users
import git.io.kotlinjwt.security.application.usecase.InMemoryUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class UserManagementConfig {
    private val users : UserDetails = Users(
        username = "junnyland",
        password = "1234",
        authority = listOf("USER")
    )

    @Bean
    fun userDetailsService(): UserDetailsService = InMemoryUserDetailsService(listOf(users))

    @Bean
    fun passwordEncoder(): PasswordEncoder = createDelegatingPasswordEncoder()

}