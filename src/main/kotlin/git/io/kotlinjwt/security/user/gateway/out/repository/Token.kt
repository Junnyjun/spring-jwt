package git.io.kotlinjwt.security.user.gateway.out.repository

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String,
    val token: String,
    val identifier: String,


    ) {
}