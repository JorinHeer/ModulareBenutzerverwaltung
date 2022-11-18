package ch.bbw.jh.benutzerverwaltung.user.registerUser;

import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUser {

    @NotEmpty(message = "name may not be empty" )
    @Size(min=2, max=24, message="Die Länge des Vornamens: 2 bis 25 Zeichen")
    private String name;


    @NotEmpty (message = "lastname may not be empty" )
    @Size(min=2, max=24, message="Die Länge des Nachnamens: 2 bis 25 Zeichen")
    private String lastname;


    @NotEmpty(message = "password may not be empty" )
    @Pattern(message = "Passwort braucht mindestens 8 Zeichen, einen Grossbuchstaben, einen Kleinbuchstaben, eine Zahl und ein Sonderzeichen.",
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[$@!%?&]).{8,20}$")
    private String password;


    @NotEmpty(message = "confirmation may not be empty" )
    @Pattern(message = "Passwort braucht mindestens 8 Zeichen, einen Grossbuchstaben, einen Kleinbuchstaben, eine Zahl und ein Sonderzeichen.",
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[$@!%?&]).{8,20}$")
    private String confirmation;

    private String message;
    @Email(message = "gib eine gültige E-Mail Adresse ein")
    private String email;
    @Pattern(regexp = "^^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "gib eine gülige Telefonnummer an")
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getConfirmation() {
        return confirmation;
    }
    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message+"\n";
    }

    public Benutzer toUser() {
        Benutzer benutzer = new Benutzer();
        benutzer.setName(name);
        benutzer.setLastname(lastname);
        benutzer.setPhonenumber(phone);
        benutzer.setEmail(email);
        benutzer.setRole("user");
        benutzer.setBenutzername(name.toLowerCase().trim()+"."+lastname.toLowerCase().trim());
        benutzer.setPassword(encode(password));

        return benutzer;
    }
    public String encode(String passw){
        int strenght = 10;
        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
        String encodedPassw = sCryptPasswordEncoder.encode(passw);
        return encodedPassw;
    }

    @Override
    public String toString() {
        return "RegisterUser [prename=" + name + ", lastname=" + lastname + ", password=" + password
                + ", confirmation=" + confirmation + "]";
    }
}
