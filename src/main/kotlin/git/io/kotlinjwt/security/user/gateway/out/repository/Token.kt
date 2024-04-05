package git.io.kotlinjwt.security.user.gateway.out.repository

import jakarta.persistence.*
import java.util.*

@Entity
class Token(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    val token: UUID,
    val identifier: String,
) {
    constructor() : this(0, UUID.randomUUID(), "JUNNYLAND")
}