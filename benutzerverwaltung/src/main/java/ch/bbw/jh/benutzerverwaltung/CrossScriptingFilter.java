package ch.bbw.jh.benutzerverwaltung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter
public class CrossScriptingFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("In doFilter CrossScriptingFilter  ...............");
        chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
        logger.info("Out doFilter CrossScriptingFilter ...............");
    }

}