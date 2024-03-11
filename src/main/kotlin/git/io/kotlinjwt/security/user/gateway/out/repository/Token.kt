package git.io.kotlinjwt.security.user.gateway.out.repository

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val token: UUID,
    val identifier: String,
) {
    constructor() : this(0, UUID.randomUUID(), "JUNNYLAND")
}