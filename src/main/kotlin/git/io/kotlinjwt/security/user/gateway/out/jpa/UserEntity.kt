package git.io.kotlinjwt.security.user.gateway.out.jpa

import jakarta.persistence.*
import java.time.LocalDateTime

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
    val role : String,
    @Column(name = "enabled", nullable = false)
    val enabled : Boolean,
    @Column(name = "created_at", nullable = false)
    val createdAt : LocalDateTime,
    @Column(name = "updated_at", nullable = false)
    val updatedAt : LocalDateTime
) {


}