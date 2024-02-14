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
) {


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .httpBasic { }
        .authorizeHttpRequests { it.anyRequest().authenticated() }
        .build()

}