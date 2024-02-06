package git.io.kotlinjwt.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User.withUsername
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class UserManagementConfig {

    @Bean
    fun userDetailsService(): UserDetailsService = InMemoryUserDetailsManager()
        .also { it.createUser(withUsername("junnyland").password("1234").roles("USER").build()) }


    @Bean
    fun passwordEncoder(): PasswordEncoder = createDelegatingPasswordEncoder()

}