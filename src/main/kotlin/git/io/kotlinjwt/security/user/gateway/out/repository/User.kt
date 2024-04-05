package git.io.kotlinjwt.security.user.gateway.out.repository

import jakarta.persistence.*

@Entity
class User(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    val username: String,
    val password: String,

    @Enumerated(EnumType.STRING)
    val algorithm: EncryptAlgorithm,

    @OneToMany
    @JoinColumn(name = "user")
    val authorities: List<Authority>
) {

    enum class EncryptAlgorithm {
        BCRYPT, SCRYPT
    }
}