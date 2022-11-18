package ch.bbw.jh.benutzerverwaltung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccessListener implements
        ApplicationListener<LogoutSuccessEvent> {
    private static final Logger logger = LoggerFactory.getLogger(LogoutSuccessListener.class);
    @Override
    public void onApplicationEvent(LogoutSuccessEvent event) {
        logger.info("User has logged out");
    }

}
