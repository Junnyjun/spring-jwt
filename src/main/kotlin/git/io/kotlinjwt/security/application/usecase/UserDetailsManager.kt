package git.io.kotlinjwt.security.application.usecase

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

interface UserDetailsManager : UserDetailsService {
    fun createUser(user: UserDetails)
    fun updateUser(username: UserDetails)
    fun deleteUser(username: String)
    fun changePassword(old: String, new: String)
    fun userExists(username: String): Boolean
}