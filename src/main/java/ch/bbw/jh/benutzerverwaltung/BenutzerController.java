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

/**
 * The type Benutzer controller.
 */
@Controller
public class BenutzerController {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    /**
     * The Benutzer service.
     */
    @Autowired
    BenutzerService benutzerService;

    /**
     * Gets request users.
     *
     * @param model the model
     * @return the request users
     */
    @GetMapping("/get-users")
    public String getRequestUsers(Model model) {
        logger.info(getCurrentUser()+": getRequestUsers");
        model.addAttribute("users", benutzerService.getAll());
        return "users";
    }

    /**
     * Delete user string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam(name = "id", required = true) long id, Model model) {
        logger.info(getCurrentUser()+": deleteUser: " + id);
        benutzerService.deleteById(id);
        return "redirect:/get-users";
    }

    /**
     * Block user string.
     *
     * @param id    the id
     * @param model the model
     * @return the string
     */
    @GetMapping("/block-user")
    public String blockUser(@RequestParam(name = "id", required = true) long id, Model model) {
        logger.info(getCurrentUser()+": deleteUser: " + id);
        Benutzer benutzer = benutzerService.getById(id);
        benutzer.toggleEnabled();
        return "redirect:/get-users";
    }

    /**
     * Get current user string.
     *
     * @return the string
     */
    public String getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().toLowerCase().trim();
    }
}
