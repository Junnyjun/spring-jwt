package git.io.kotlinjwt.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.ObjectPostProcessor
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val objectPostProcessor: ObjectPostProcessor<Any>
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .httpBasic { }
        .authorizeHttpRequests { it.anyRequest().authenticated() }
        .authenticationManager(authenticationManager(AuthenticationManagerBuilder(objectPostProcessor)))
        .authenticationProvider()
        .build()

    private fun corsConfiguration(): CorsConfiguration = CorsConfiguration().apply {
        allowedOrigins = listOf("junnyland.com")
        allowedMethods = listOf("*")
        allowedHeaders = listOf("*")
        allowCredentials = true
    }

    fun userDetailsService(): UserDetailsService = InMemoryUserDetailsManager()
        .also {
            it.createUser(
                User
                    .withUsername("junnyland").password("1234").roles("USER").build()
            )
        }
    fun authenticationManager(auth: AuthenticationManagerBuilder): AuthenticationManager = auth
            .also {
                it
                    .userDetailsService<UserDetailsService>(userDetailsService())
                    .passwordEncoder(passwordEncoder())
            }
            .build()
}