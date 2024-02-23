package git.io.kotlinjwt.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val customAuthenticationProvider: CustomAuthenticationProvider
) {


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .formLogin { it.loginPage("/login")
            .successHandler { _, response, _ -> response.sendRedirect("/hello") }
            .failureHandler { _, response, _ -> response.sendRedirect("/error") }
        }
        .build()


    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder(4)

    @Bean
    fun sCryptPasswordEncoder(): PasswordEncoder = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()

}