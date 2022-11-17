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
public class ProfileController {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired
    BenutzerService benutzerService;
    @GetMapping("/edit-profile")
    public String editProfile(Model model) {
        logger.info(getCurrentUser()+": call edit profile");
        Benutzer user = benutzerService.getByUserName(getCurrentUser());
        ChangeProfile changeProfile = new ChangeProfile();
        changeProfile.toChangeProfile(user);
        model.addAttribute("changeProfile", changeProfile);
        return "edit";
    }
    @PostMapping("/edit-profile")
    public String postRequestEditUsers(ChangeProfile changeProfile) {
        logger.info(getCurrentUser()+": Post edit profile");
        String redirect = "login?logout";
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ChangeProfile>> constraintViolations =
                validator.validate( changeProfile );
        if (!changeProfile.getPassword().isEmpty()&& !changeProfile.getPassword().equals("")){
            if (!changeProfile.getPassword().equals(changeProfile.getConfirmation())) {
                    changeProfile.setMessage("Password and Confirmation not the same!");
                    logger.info(getCurrentUser()+": failed profile change, username");
                    return "edit";
            }
        }
        if (benutzerService.getByUserName(changeProfile.getName().toLowerCase()
                + "." + changeProfile.getLastname().toLowerCase()) != null) {
            redirect = "index";
            if (!benutzerService.getByUserName(changeProfile.getName().toLowerCase()
                    + "." + changeProfile.getLastname().toLowerCase()).getId().equals(changeProfile.getId())){
                changeProfile.setMessage("Username " +
                        changeProfile.getName().toLowerCase() + "." + changeProfile.getLastname().toLowerCase()
                        + " allready exists");
                logger.info(getCurrentUser()+": failed profile change, username");
                return "edit";
            }

        }
        if (!constraintViolations.isEmpty()) {
            logger.info(getCurrentUser()+": failed profile change");

            changeProfile.setMessage(constraintViolations.iterator().next().getMessage());
            return "edit";

        }
            logger.info(getCurrentUser()+": successfully changed user: "+changeProfile.toUser().getBenutzername());
            Benutzer user = benutzerService.getById(changeProfile.getId());
            user.setName(changeProfile.getName());
            user.setLastname(changeProfile.getLastname());
            user.setPhonenumber(changeProfile.getPhone());
            user.setEmail(changeProfile.getEmail());
            user.setBenutzername(changeProfile.getName().toLowerCase().trim()+"."+changeProfile.getLastname().toLowerCase().trim());
            if (!changeProfile.getPassword().isEmpty()&& !changeProfile.getPassword().equals("")){
                user.setPassword(changeProfile.encode(changeProfile.getPassword()));
            }
            benutzerService.update(changeProfile.getId(), user);
            return "redirect:/"+redirect;

    }
    public String getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().toLowerCase().trim();
    }
}
