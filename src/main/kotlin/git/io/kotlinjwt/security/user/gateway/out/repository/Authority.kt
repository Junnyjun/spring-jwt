package git.io.kotlinjwt.security.user.gateway.out.repository

import jakarta.persistence.*

@Entity
class Authority(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "user")
    val user: User
) {

}