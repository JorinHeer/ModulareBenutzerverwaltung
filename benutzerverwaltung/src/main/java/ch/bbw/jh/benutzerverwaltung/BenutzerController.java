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
import org.springframework.web.bind.annotation.PostMapping;
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
    //loads user editing Page
    @GetMapping("/edit-user")
    public String editUser(@RequestParam(name = "id", required = true) long id, Model model) {
        Benutzer user = benutzerService.getById(id);
        logger.info(getCurrentUser()+": editUser get: " + user);
        model.addAttribute("user", user);
        return "changeuser";
    }
    @GetMapping("/edit-profile")
    public String editProfile(Model model) {
        logger.info(getCurrentUser()+": call edit profile");
        Benutzer benutzer = benutzerService.getByUserName(getCurrentUser());
        model.addAttribute("user", benutzer);
        return "edit";

    }
    //saves user changes
    @PostMapping("/edit-user")
    public String editUser(Benutzer benutzer, Model model) {
        logger.info(getCurrentUser()+": editUser post: edit user" + benutzer.getId());
        Benutzer value = benutzerService.getById(benutzer.getId());
        value.setRole(benutzer.getRole());
        value.setName(benutzer.getName());
        value.setLastname(benutzer.getLastname());
        value.setPhonenumber(benutzer.getPhonenumber());
        value.setEmail(benutzer.getEmail());
        value.setBenutzername(benutzer.getName().trim().toLowerCase()+"."+benutzer.getLastname().trim().toLowerCase());
        logger.info(getCurrentUser()+": editUser post: update user" + value.getBenutzername());
        benutzerService.update(benutzer.getId(), value);
        return "redirect:/get-users";
    }

    //saves user changes
    @PostMapping("/edit-profile")
    public String editProfile(Benutzer benutzer, Model model) {
        logger.info(getCurrentUser()+": editProfile post: edit user" + benutzer.getId());
        Benutzer value = benutzerService.getByUserName(getCurrentUser());
        value.setName(benutzer.getName());
        value.setLastname(benutzer.getLastname());
        value.setPhonenumber(benutzer.getPhonenumber());
        value.setEmail(benutzer.getEmail());
        value.setBenutzername(benutzer.getName().trim().toLowerCase()+"."+benutzer.getLastname().trim().toLowerCase());
        logger.info(getCurrentUser()+": editUser post: update user" + value.getBenutzername());
        benutzerService.update(benutzer.getId(), value);
        return "redirect:/index";
    }
    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam(name = "id", required = true) long id, Model model) {
        logger.info(getCurrentUser()+": deleteUser: " + id);
        benutzerService.deleteById(id);
        return "redirect:/get-users";
    }
    public String getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().toLowerCase().trim();
    }
}
