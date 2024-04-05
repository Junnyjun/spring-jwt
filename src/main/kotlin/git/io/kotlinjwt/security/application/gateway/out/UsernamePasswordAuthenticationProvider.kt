package git.io.kotlinjwt.security.application.gateway.out

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class UsernamePasswordAuthenticationProvider(

//    private val sendOTPGateway: SendOTPGateway
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        val name = authentication.name
        val password = authentication.credentials.toString()

//        sendOTPGateway.send(name, password)
        return  UsernamePasswordAuthentication(name, password, null)
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthentication::class.java.isAssignableFrom(authentication)
    }
}