package ch.bbw.jh.benutzerverwaltung.user.changeUser;

import ch.bbw.jh.benutzerverwaltung.BenutzerController;
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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Controller
public class ChangeController {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired
    BenutzerService benutzerService;

    @GetMapping("/edit-user")
    public String editUser(@RequestParam(name = "id", required = true) long id, Model model) {
        Benutzer user = benutzerService.getById(id);
        ChangeUser changeUser = new ChangeUser();
        changeUser.toChangeUser(user);
        logger.info(getCurrentUser()+": editUser get: " + user);
        model.addAttribute("changeUser", changeUser);
        return "changeuser";
    }
    @PostMapping("/edit-user")
    public String postRequestEditUsers(ChangeUser changeUser, Model model) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ChangeUser>> constraintViolations =
                validator.validate( changeUser );
        if (!changeUser.getPassword().isEmpty()&& changeUser.equals("")) {
            if (!changeUser.getPassword().equals(changeUser.getConfirmation())) {
                changeUser.setMessage("Password and Confirmation not the same!");
                logger.info(getCurrentUser()+": failed profile change");
                return "redirect:/edit-user?id=" + changeUser.getId();
            }
        }
        if (benutzerService.getByUserName(changeUser.getName().toLowerCase()
                + "." + changeUser.getLastname().toLowerCase()) != null) {
            changeUser.setMessage("Username " +
                    changeUser.getName().toLowerCase() + "." + changeUser.getLastname().toLowerCase()
                    + " allready exists");
            if (!benutzerService.getByUserName(changeUser.getName().toLowerCase()
                    + "." + changeUser.getLastname().toLowerCase()).getId().equals(changeUser.getId())){
                changeUser.setMessage("Username " +
                        changeUser.getName().toLowerCase() + "." + changeUser.getLastname().toLowerCase()
                        + " allready exists");
                logger.info(getCurrentUser()+": failed profile change");
                return "redirect:/edit-user?id=" + changeUser.getId();
            }
        }
        if (!constraintViolations.isEmpty()) {
            logger.info(getCurrentUser()+": failed profile change");
            changeUser.setMessage(constraintViolations.iterator().next().getMessage());
            return "redirect:/edit-user?id="+changeUser.getId();


        }else {
            logger.info(getCurrentUser()+": successfully changed user: "+changeUser.toUser().getBenutzername());

            Benutzer user = benutzerService.getById(changeUser.getId());
            user.setName(changeUser.getName());
            user.setLastname(changeUser.getLastname());
            user.setPhonenumber(changeUser.getPhone());
            user.setEmail(changeUser.getEmail());
            user.setBenutzername(changeUser.getName().toLowerCase().trim()+"."+changeUser.getLastname().toLowerCase().trim());
            if (!changeUser.getPassword().equals("")|| !changeUser.getPassword().isEmpty()) {
                user.setPassword(changeUser.getPassword());
            }
            benutzerService.update(changeUser.getId(), user);
            return "redirect:/index";
        }
    }
    public String getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().toLowerCase().trim();
    }
}
