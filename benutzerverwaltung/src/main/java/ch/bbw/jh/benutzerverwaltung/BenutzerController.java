package ch.bbw.jh.benutzerverwaltung;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BenutzerController {
    @Autowired
    BenutzerService benutzerService;

    @GetMapping("/get-users")
    public String getRequestUsers(Model model) {
        System.out.println("getRequestUsers");
        benutzerService.add(new Benutzer("name"," dfasdf", "123123", "sdfadf","qqwerqr","USER"));
        model.addAttribute("users", benutzerService.getAll());
        return "users";
    }

    @GetMapping("/edit-users")
    public String editMember(@RequestParam(name = "id", required = true) long id, Model model) {
        Benutzer member = benutzerService.getById(id);
        System.out.println("editUser get: " + member);
     model.addAttribute("user", member);
          return "edit";
    }


    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam(name = "id", required = true) long id, Model model) {
        System.out.println("deleteMember: " + id);
        benutzerService.deleteById(id);
        return "redirect:/get-users";
    }
}
