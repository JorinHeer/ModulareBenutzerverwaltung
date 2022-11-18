package ch.bbw.jh.benutzerverwaltung.user.changeProfile;

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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Controller
public class ProfileFormController {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired
    BenutzerService benutzerService;
    @GetMapping("/edit-profile")
    public String editProfile(Model model) {
        logger.info(getCurrentUser()+": call edit profile");
        Benutzer user = benutzerService.getByUserName(getCurrentUser());
        EditProfile editProfile = new EditProfile();
        editProfile.toChangeProfile(user);
        model.addAttribute("editProfile", editProfile);
        return "edit";
    }
    @PostMapping("/edit-profile")
    public String postRequestEditUsers(EditProfile editProfile) {
        logger.info(getCurrentUser()+": Post edit profile");
        String redirect = "login?logout";
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<EditProfile>> constraintViolations =
                validator.validate(editProfile);
        if (!editProfile.getPassword().isEmpty()&& !editProfile.getPassword().equals("")){
            if (!editProfile.getPassword().equals(editProfile.getConfirmation())) {
                    editProfile.setMessage("Password and Confirmation not the same!");
                    logger.info(getCurrentUser()+": failed profile change, username");
                    return "edit";
            }
        }
        if (benutzerService.getByUserName(editProfile.getName().toLowerCase()
                + "." + editProfile.getLastname().toLowerCase()) != null) {
            redirect = "index";
            if (!benutzerService.getByUserName(editProfile.getName().toLowerCase()
                    + "." + editProfile.getLastname().toLowerCase()).getId().equals(editProfile.getId())){
                editProfile.setMessage("Username " +
                        editProfile.getName().toLowerCase() + "." + editProfile.getLastname().toLowerCase()
                        + " allready exists");
                logger.info(getCurrentUser()+": failed profile change, username");
                return "edit";
            }

        }
        if (!constraintViolations.isEmpty()) {
            logger.info(getCurrentUser()+": failed profile change");

            editProfile.setMessage(constraintViolations.iterator().next().getMessage());
            return "edit";

        }
            logger.info(getCurrentUser()+": successfully changed user: "+ editProfile.toUser().getBenutzername());
            Benutzer user = benutzerService.getById(editProfile.getId());
            user.setName(editProfile.getName());
            user.setLastname(editProfile.getLastname());
            user.setPhonenumber(editProfile.getPhone());
            user.setEmail(editProfile.getEmail());
            user.setBenutzername(editProfile.getName().toLowerCase().trim()+"."+ editProfile.getLastname().toLowerCase().trim());
            if (!editProfile.getPassword().isEmpty()&& !editProfile.getPassword().equals("")){
                user.setPassword(editProfile.encode(editProfile.getPassword()));
            }
            benutzerService.update(editProfile.getId(), user);
            return "redirect:/"+redirect;

    }
    public String getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().toLowerCase().trim();
    }
}
