package ch.bbw.jh.benutzerverwaltung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Authentication success event listener.
 */
@Component
public class AuthenticationSuccessEventListener implements
        ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    /**
     * On application event.
     *
     * @param e the e
     */
    @Override
    public void onApplicationEvent(final AuthenticationSuccessEvent e) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            loginAttemptService.loginSucceeded(request.getRemoteAddr());
            logger.info(request.getRemoteAddr()+" User logged in");
        } else {
            loginAttemptService.loginSucceeded(xfHeader.split(",")[0]);
        }
    }

}
