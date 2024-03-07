package git.io.kotlinjwt.security.application.gateway.`in`

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private const val KEY: String = "JUNNYLAND"

class CustomFilter : Filter{
    private val logger = LoggerFactory.getLogger(CustomFilter::class.java)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filter: FilterChain) {
        logger.info("custom doFilter")
        val httpRequest = request as HttpServletRequest
        httpRequest.getHeader("JUNNYLAND")
            ?.takeIf { it == KEY }
            ?.let { filter.doFilter(request, response) }
            ?: throw RuntimeException("Invalid token")
    }
}