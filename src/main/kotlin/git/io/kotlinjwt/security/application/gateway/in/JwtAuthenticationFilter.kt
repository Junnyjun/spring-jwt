package git.io.kotlinjwt.security.application.gateway.`in`

import git.io.kotlinjwt.security.application.gateway.out.OtpAuthentication
import git.io.kotlinjwt.security.application.gateway.out.UsernamePasswordAuthentication
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val manger: AuthenticationManager,
    @Value("\${security.jwt.secret}") private val secret: String
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")

        val body:Claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secret.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body

        val username = body["username"].toString()
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthentication(username, null, null)
        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean = request.requestURI.contains("/login")

    private fun createJwt(username: String): String = Jwts.builder()
        .setClaims(mapOf("username" to username))
        .signWith(Keys.hmacShaKeyFor(secret.toByteArray()))
        .compact()
}