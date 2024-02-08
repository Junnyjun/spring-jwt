package git.io.kotlinjwt.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.ObjectPostProcessor
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val objectPostProcessor: ObjectPostProcessor<Any>,
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .httpBasic { }
        .authorizeHttpRequests { it.anyRequest().authenticated() }
//        .authenticationProvider(authenticationProvider)
        .build()

//    @Component
//    class CustomAuthenticationProvider: AuthenticationProvider {
//        override fun authenticate(authentication: Authentication): Authentication {
//            val username = authentication.name
//            val password = authentication.credentials.toString()
//            if (username == "junnyland" && password == "1234") {
//                return UsernamePasswordAuthenticationToken(username, password, emptyList())
//            }
//            throw BadCredentialsException("Authentication failed for user $username")
//        }
//        override fun supports(authentication: Class<*>): Boolean {
//            return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
//        }
//    }
}