package ch.bbw.jh.benutzerverwaltung;


import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import ch.bbw.jh.benutzerverwaltung.user.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BenutzerController {
    @Autowired
    BenutzerService benutzerService;

    @GetMapping("/get-users")
    public String getRequestUsers(Model model) {
        System.out.println("getRequestUsers");
        model.addAttribute("users", benutzerService.getAll());
        return "users";
    }
    //loads user editing Page
    @GetMapping("/edit-user")
    public String editUser(@RequestParam(name = "id", required = true) long id, Model model) {
        Benutzer member = benutzerService.getById(id);
        System.out.println("editUser get: " + member);
     model.addAttribute("user", member);
          return "changeuser";
    }
    //saves user changes
    @PostMapping("/edit-user")
    public String editUser(Benutzer benutzer, Model model) {
        System.out.println("editUser post: edit user" + benutzer.getId());
        Benutzer value = benutzerService.getById(benutzer.getId());
        value.setRole(benutzer.getRole());
        value.setName(benutzer.getName());
        value.setLastname(benutzer.getLastname());
        value.setPassword(benutzer.getPassword());
        value.setPhonenumber(benutzer.getPhonenumber());
        value.setEmail(benutzer.getEmail());
        value.setBenutzername(benutzer.getName().trim().toLowerCase()+"."+benutzer.getLastname().trim().toLowerCase());
        System.out.println("editUser post: update member" + value);
        benutzerService.update(benutzer.getId(), value);
        return "redirect:/get-users";
    }

    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam(name = "id", required = true) long id, Model model) {
        System.out.println("deleteUser: " + id);
        benutzerService.deleteById(id);
        return "redirect:/get-users";
    }
}
