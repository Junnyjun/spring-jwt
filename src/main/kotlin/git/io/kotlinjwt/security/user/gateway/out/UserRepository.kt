package git.io.kotlinjwt.security.user.gateway.out

import git.io.kotlinjwt.security.user.gateway.out.jpa.UserJpaRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Repository

interface UserRepository {

    @Repository
    class UserRdbmsRepository(
        private val userJpaRepository: UserJpaRepository
    ): UserRepository {
        private val log:Logger = getLogger(UserRdbmsRepository::class.java)
    }
}