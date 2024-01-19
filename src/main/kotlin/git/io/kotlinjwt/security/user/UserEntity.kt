package git.io.kotlinjwt.security.user

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long,
    @Column(name = "username", nullable = false, unique = true)
    val username : String,
    @Column(name = "password", nullable = false)
    val password : String,
    @Column(name = "role", nullable = false)
    val role : String

) {
}