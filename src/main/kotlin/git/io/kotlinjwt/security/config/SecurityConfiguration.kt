package git.io.kotlinjwt.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    @Bean
    fun passwordEncoder():PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .requiresChannel { it.anyRequest().requiresSecure() }
        .anonymous { it.disable() }
        .csrf { it.disable() }
        .authenticationManager{
            it
        }
        .cors { it.configurationSource { corsConfiguration() } }
        .portMapper { it.http(8080).mapsTo(8443) }
        .build()

    private fun corsConfiguration(): CorsConfiguration = CorsConfiguration().apply {
        allowedOrigins = listOf("junnyland.com")
        allowedMethods = listOf("*")
        allowedHeaders = listOf("*")
        allowCredentials = true
    }

}