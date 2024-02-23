package git.io.kotlinjwt.security.user.gateway.out.repository

import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository: JpaRepository<Authority, Long> {
}