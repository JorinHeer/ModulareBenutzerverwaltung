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
public class EditUserFormController {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired
    BenutzerService benutzerService;

    @GetMapping("/edit-user")
    public String editUser(@RequestParam(name = "id", required = true) long id, Model model) {
        Benutzer user = benutzerService.getById(id);
        EditUser editUser = new EditUser();
        editUser.toChangeUser(user);
        logger.info(getCurrentUser()+": editUser get: " + user);
        model.addAttribute("editUser", editUser);
        return "edituser";
    }
    @PostMapping("/edit-user")
    public String postRequestEditUsers(EditUser editUser, Model model) {
        logger.info(getCurrentUser()+": Post edit user");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EditUser>> constraintViolations =
                validator.validate(editUser);
        if (!editUser.getPassword().isEmpty()&& !editUser.getPassword().equals("")) {
            if (!editUser.getPassword().equals(editUser.getConfirmation())) {
                editUser.setMessage("Password and Confirmation not the same!");
                logger.info(getCurrentUser()+": failed profile change");
                return "redirect:/edit-user?id=" + editUser.getId();
            }
        }
        if (benutzerService.getByUserName(editUser.getName().toLowerCase()
                + "." + editUser.getLastname().toLowerCase()) != null) {
            if (!benutzerService.getByUserName(editUser.getName().toLowerCase()
                    + "." + editUser.getLastname().toLowerCase()).getId().equals(editUser.getId())){
                editUser.setMessage("Username " +
                        editUser.getName().toLowerCase() + "." + editUser.getLastname().toLowerCase()
                        + " allready exists");
                logger.info(getCurrentUser()+": failed profile change");
                return "redirect:/edit-user?id=" + editUser.getId();
            }
        }
        if (!constraintViolations.isEmpty()) {
            logger.info(getCurrentUser()+": failed profile change");
            editUser.setMessage(constraintViolations.iterator().next().getMessage());
            return "redirect:/edit-user?id="+ editUser.getId();


        }else {
            logger.info(getCurrentUser()+": successfully changed user: "+ editUser.toUser().getBenutzername());

            Benutzer user = benutzerService.getById(editUser.getId());
            user.setName(editUser.getName());
            user.setLastname(editUser.getLastname());
            user.setPhonenumber(editUser.getPhone());
            user.setEmail(editUser.getEmail());
            user.setBenutzername(editUser.getName().toLowerCase().trim()+"."+ editUser.getLastname().toLowerCase().trim());
            if (!editUser.getPassword().equals("")|| !editUser.getPassword().isEmpty()) {
                user.setPassword(editUser.encode(editUser.getPassword()));
            }
            benutzerService.update(editUser.getId(), user);
            return "redirect:/index";
        }
    }
    public String getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().toLowerCase().trim();
    }
}
