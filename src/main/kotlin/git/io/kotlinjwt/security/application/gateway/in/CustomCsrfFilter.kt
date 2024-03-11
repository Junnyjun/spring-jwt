package git.io.kotlinjwt.security.application.gateway.`in`

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.LoggerFactory

class CustomCsrfFilter : Filter {
    private val logger = LoggerFactory.getLogger(CustomFilter::class.java)
    override fun doFilter(req: ServletRequest, res: ServletResponse, filter: FilterChain) {
        val csrf = req.getAttribute("_csrf")
        filter.doFilter(req, res)
    }
}