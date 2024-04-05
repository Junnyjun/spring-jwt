package git.io.kotlinjwt.security.config

import git.io.kotlinjwt.security.application.gateway.`in`.InitialAuthenticationFilter
import git.io.kotlinjwt.security.application.gateway.`in`.JwtAuthenticationFilter
import git.io.kotlinjwt.security.application.gateway.out.OtpAuthenticationProvider
import git.io.kotlinjwt.security.application.gateway.out.UsernamePasswordAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val initial: InitialAuthenticationFilter,
    private val jwt: JwtAuthenticationFilter,
    private val otpProvider: OtpAuthenticationProvider,
    private val username: UsernamePasswordAuthenticationProvider
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain = http
        .addFilterBefore(initial, BasicAuthenticationFilter::class.java)
        .addFilterBefore(jwt, BasicAuthenticationFilter::class.java)
        .authorizeHttpRequests { authorizeRequests ->
            authorizeRequests
                .anyRequest().authenticated()
        }
        .build()

    @Bean
    fun configure(auth: AuthenticationManagerBuilder): AuthenticationManager? {
        return auth.authenticationProvider(username)
            .authenticationProvider(otpProvider)
            .build()
    }

    @Bean
    fun authenticationManager(auth: AuthenticationManagerBuilder): AuthenticationManager = auth.build()
}