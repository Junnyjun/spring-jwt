package git.io.kotlinjwt.security.user.gateway.out

import git.io.kotlinjwt.security.user.gateway.out.jpa.UserJpaRepository
import org.slf4j.Logger
import org.springframework.stereotype.Repository

interface UserRepository {

    @Repository
    class UserRdbmsRepository(
        private val userJpaRepository: UserJpaRepository
    ): UserRepository {
        private val log:Logger = org.slf4j.LoggerFactory.getLogger(UserRdbmsRepository::class.java)
    }
}