package git.io.kotlinjwt.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.ObjectPostProcessor
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.stereotype.Component
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val objectPostProcessor: ObjectPostProcessor<Any>,
    private val authenticationProvider: AuthenticationProvider
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .httpBasic { }
        .authorizeHttpRequests { it.anyRequest().authenticated() }
        .authenticationProvider(authenticationProvider)
        .build()

    @Component
    class CustomAuthenticationProvider: AuthenticationProvider {
        override fun authenticate(authentication: Authentication): Authentication {
            val username = authentication.name
            val password = authentication.credentials.toString()
            if (username == "junnyland" && password == "1234") {
                return UsernamePasswordAuthenticationToken(username, password, emptyList())
            }
            throw BadCredentialsException("Authentication failed for user $username")
        }
        override fun supports(authentication: Class<*>): Boolean {
            return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
        }
    }
}