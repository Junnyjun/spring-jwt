package git.io.kotlinjwt.security.application.gateway.out

import git.io.kotlinjwt.security.user.gateway.out.repository.Token
import git.io.kotlinjwt.security.user.gateway.out.repository.TokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.DefaultCsrfToken
import java.util.*

class CsrfTokenRdbmsRepository(
    private val csrfTokenRepository: TokenRepository
) : CsrfTokenRepository {
    override fun generateToken(request: HttpServletRequest): CsrfToken =
        DefaultCsrfToken("X-CSRF-TOKEN", "_csrf","${UUID.randomUUID()}")

    override fun saveToken(token: CsrfToken, request: HttpServletRequest, response: HttpServletResponse) {
        csrfTokenRepository.findByIdentifier(request.getHeader("X-IDENTIFIER"))
            ?.let { csrfTokenRepository.save((Token(token = UUID.fromString(token.token), identifier = "JUNNYLAND"))) }
            ?: csrfTokenRepository.save(Token(token = UUID.fromString(token.token), identifier = "JUNNYLAND"))
    }
    override fun loadToken(request: HttpServletRequest): CsrfToken? =
        csrfTokenRepository.findByIdentifier(request.getHeader("X-IDENTIFIER"))
            ?.let { DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", it.token.toString()) }
}