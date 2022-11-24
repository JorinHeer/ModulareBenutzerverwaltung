package ch.bbw.jh.benutzerverwaltung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Cross scripting filter.
 */
@WebFilter
public class CrossScriptingFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CrossScriptingFilter.class);
    private FilterConfig filterConfig;

    /**
     * Init.
     *
     * @param filterConfig the filter config
     * @throws ServletException the servlet exception
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy.
     */
    public void destroy() {
        this.filterConfig = null;
    }

    /**
     * Do filter.
     *
     * @param request  the request
     * @param response the response
     * @param chain    the chain
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("In doFilter CrossScriptingFilter  ...............");
        chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
        logger.info("Out doFilter CrossScriptingFilter ...............");
    }

}