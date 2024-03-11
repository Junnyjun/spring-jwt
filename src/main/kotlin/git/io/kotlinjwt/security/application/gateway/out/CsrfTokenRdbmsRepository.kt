package git.io.kotlinjwt.security.application.gateway.out

import git.io.kotlinjwt.security.user.gateway.out.repository.Token
import git.io.kotlinjwt.security.user.gateway.out.repository.TokenRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository

class CsrfTokenRdbmsRepository(
    private val csrfTokenRepository: TokenRepository
) : CsrfTokenRepository {
    override fun generateToken(request: HttpServletRequest?): CsrfToken {
        return csrfTokenRepository.save(Token())
    }

    override fun saveToken(token: CsrfToken?, request: HttpServletRequest?, response: HttpServletResponse?) {
        TODO("Not yet implemented")
    }

    override fun loadToken(request: HttpServletRequest?): CsrfToken {
        TODO("Not yet implemented")
    }
}