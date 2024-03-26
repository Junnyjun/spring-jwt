package git.io.kotlinjwt.security.application.gateway.`in`

import git.io.kotlinjwt.security.application.gateway.out.OtpAuthentication
import git.io.kotlinjwt.security.application.gateway.out.UsernamePasswordAuthentication
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class InitialAuthenticationFilter(
    private val manger: AuthenticationManager,
    @Value("\${security.jwt.secret}") private val secret: String
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val username = request.getHeader("username")
        val password = request.getHeader("password")

        request.getHeader("code")
            ?.let { code -> OtpAuthentication(username, code, null)}
            ?.let { manger.authenticate(it) }
            ?.let { Jwts. }
            ?: manger.authenticate(UsernamePasswordAuthentication(username, password, null))

    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean = request.requestURI.contains("/login")

}