package ch.bbw.jh.benutzerverwaltung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


public class LoginLogoutController {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @PostMapping("/login")
    public String login(Model model){
        logger.info("Unregistered User tried to login");
        return "login";
    }

    @PostMapping("/logout")
    public String logout(Model model){
        logger.info("User logout");
        return "logout";
    }




}
