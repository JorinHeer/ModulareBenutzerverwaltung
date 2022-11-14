package ch.bbw.jh.benutzerverwaltung;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginLogoutController {
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        return "logout";
    }
}
