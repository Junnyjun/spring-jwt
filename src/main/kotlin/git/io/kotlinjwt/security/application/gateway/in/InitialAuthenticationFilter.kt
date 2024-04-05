package git.io.kotlinjwt.security.application.gateway.`in`

import git.io.kotlinjwt.security.application.gateway.out.OtpAuthentication
import git.io.kotlinjwt.security.application.gateway.out.UsernamePasswordAuthentication
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.filter.OncePerRequestFilter

//@Component
class InitialAuthenticationFilter(
    @Qualifier("authenticationManager") private val manger: AuthenticationManager,
    @Value("\${jwt.secret}") private val secret: String
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
            ?.let { createJwt(username) }
            ?.let { jwt -> response.setHeader("Authorization", jwt) }
            ?: manger.authenticate(UsernamePasswordAuthentication(username, password, null))
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean = request.requestURI.contains("/login")

    private fun createJwt(username: String): String = Jwts.builder()
        .setClaims(mapOf("username" to username))
        .signWith(Keys.hmacShaKeyFor(secret.toByteArray()))
        .compact()
}