package git.io.kotlinjwt.security.config

import git.io.kotlinjwt.security.application.usecase.CustomAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val authenticationProvider: CustomAuthenticationProvider
) {


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .authorizeHttpRequests {
            it
                .requestMatchers("/write").hasAuthority("WRITE")
                .requestMatchers("/read").hasAnyAuthority("READ", "WRITE")
        }
        .authenticationProvider(authenticationProvider)
        .build()

}