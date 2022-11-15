package ch.bbw.jh.benutzerverwaltung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.*;
import java.util.Set;

@Controller
@Validated
public class RegisterController {
    @Autowired
    BenutzerService benutzerService;

    @GetMapping("/get-register")
    public String getRequestRegistMembers(Model model) {
        model.addAttribute("registerUser", new RegisterUser());
        return "register";
    }

    @PostMapping("/get-register")
    public String postRequestRegistMembers(RegisterUser registerUser, BindingResult bindingResult, Model model) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegisterUser>> constraintViolations =
                validator.validate( registerUser );
        if (!constraintViolations.isEmpty()) {

            if (!registerUser.getPassword().equals(registerUser.getConfirmation())) {
                System.out.println("Password and Confirmation not the same!");
                registerUser.setMessage("Password and Confirmation not the same!");
                return "register";
            }
            if (benutzerService.getByUserName(registerUser.getName().toLowerCase()
                    + "." + registerUser.getLastname().toLowerCase()) != null) {
                System.out.println("User allready exists, choose other first- or lastname.");
                registerUser.setMessage("Username " +
                        registerUser.getName().toLowerCase() + "." + registerUser.getLastname().toLowerCase()
                        + " allready exists");
                return "register";
            }
            for (ConstraintViolation<RegisterUser> violaton:
                 constraintViolations) {
                registerUser.setMessage(registerUser.getMessage()+violaton.getMessage()+"\n");
            }
            return "register";

        }else {
            System.out.println(registerUser);

            benutzerService.add(registerUser.toUser());
            model.addAttribute("Username", benutzerService.getByUserName(registerUser.toUser().getBenutzername()));
            return "register";
        }
    }
}
