package ch.bbw.jh.benutzerverwaltung.user.registerUser;

import ch.bbw.jh.benutzerverwaltung.BenutzerController;
import ch.bbw.jh.benutzerverwaltung.user.BenutzerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Controller
@Validated
public class RegisterFormController {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerController.class);
    @Autowired
    BenutzerService benutzerService;

    @GetMapping("/get-register")
    public String getRequestRegistUsers(Model model) {
        logger.info(getCurrentUser()+": registerUser");
        model.addAttribute("registerUser", new RegisterUser());
        return "register";
    }

    @PostMapping("/get-register")
    public String postRequestRegistUsers(RegisterUser registerUser, Model model) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegisterUser>> constraintViolations =
                validator.validate( registerUser );
        if (!constraintViolations.isEmpty()) {
            logger.info(getCurrentUser()+": failed registration");
            if (!registerUser.getPassword().equals(registerUser.getConfirmation())) {

                registerUser.setMessage("Password and Confirmation not the same!");
                return "register";
            }
            if (benutzerService.getByUserName(registerUser.getName().toLowerCase()
                    + "." + registerUser.getLastname().toLowerCase()) != null) {
                registerUser.setMessage("Username " +
                        registerUser.getName().toLowerCase() + "." + registerUser.getLastname().toLowerCase()
                        + " allready exists");
                return "register";
            }
                registerUser.setMessage(constraintViolations.iterator().next().getMessage());
            return "register";

        }else {
            logger.info(getCurrentUser()+": successfully added user: "+registerUser.toUser().getBenutzername());

            benutzerService.add(registerUser.toUser());
            model.addAttribute("Username", benutzerService.getByUserName(registerUser.toUser().getBenutzername()));
            return "registerconfirmed";
        }
    }
    public String getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername().toLowerCase().trim();
    }
}
