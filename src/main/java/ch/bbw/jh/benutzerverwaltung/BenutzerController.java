package ch.bbw.jh.benutzerverwaltung;


import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import ch.bbw.jh.benutzerverwaltung.user.BenutzerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BenutzerController {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired
    BenutzerService benutzerService;

    @GetMapping("/get-users")
    public String getRequestUsers(Model model) {
        logger.info(getCurrentUser()+": getRequestUsers");
        model.addAttribute("users", benutzerService.getAll());
        return "users";
    }

    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam(name = "id", required = true) long id, Model model) {
        logger.info(getCurrentUser()+": deleteUser: " + id);
        benutzerService.deleteById(id);
        return "redirect:/get-users";
    }
    @GetMapping("/block-user")
    public String blockUser(@RequestParam(name = "id", required = true) long id, Model model) {
        logger.info(getCurrentUser()+": deleteUser: " + id);
        Benutzer benutzer = benutzerService.getById(id);
        benutzer.toggleEnabled();
        return "redirect:/get-users";
    }
    public String getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().toLowerCase().trim();
    }
}
