package git.io.kotlinjwt.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import javax.sql.DataSource

@Configuration
class UserManagementConfig {


    @Bean
    fun userDetailsService(dataSource: DataSource): UserDetailsService = JdbcUserDetailsManager(dataSource)

    @Bean
    fun passwordEncoder(): PasswordEncoder = getInstance()

}