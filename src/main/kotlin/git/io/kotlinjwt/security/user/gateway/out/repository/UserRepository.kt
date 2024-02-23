package git.io.kotlinjwt.security.user.gateway.out.repository

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun findByUsername(username: String): User?
}