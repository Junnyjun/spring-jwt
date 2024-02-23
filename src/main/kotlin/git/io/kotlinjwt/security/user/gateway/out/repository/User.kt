package git.io.kotlinjwt.security.user.gateway.out.repository

import jakarta.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val username: String,
    val password: String,

    @Enumerated(EnumType.STRING)
    val algorithm: EncryptAlgorithm,

    @OneToMany
    val authorities: List<Authority>
) {

    enum class EncryptAlgorithm {
        BCRYPT, SCRYPT
    }
}