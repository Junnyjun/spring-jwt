package git.io.kotlinjwt.security.application.gateway.out

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UsernamePasswordAuthentication (
    principal: Any?,
    credentials: Any?,
    authorities: MutableCollection<out GrantedAuthority>?
): UsernamePasswordAuthenticationToken(principal, credentials, authorities) {
}