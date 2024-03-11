package git.io.kotlinjwt.security.config

import git.io.kotlinjwt.security.application.gateway.`in`.CustomFilter
import git.io.kotlinjwt.security.application.usecase.CustomAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val customFilter: CustomFilter
) {


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .csrf {
            it.ignoringRequestMatchers("/**/test")
                .csrfTokenRepository()
        }
        .build()
}