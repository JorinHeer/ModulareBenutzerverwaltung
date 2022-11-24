package ch.bbw.jh.benutzerverwaltung.user.registerUser;

import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The type Register user.
 */
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

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets confirmation.
     *
     * @return the confirmation
     */
    public String getConfirmation() {
        return confirmation;
    }

    /**
     * Sets confirmation.
     *
     * @param confirmation the confirmation
     */
    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message+"\n";
    }

    /**
     * To user benutzer.
     *
     * @return the benutzer
     */
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

    /**
     * Encode string.
     *
     * @param passw the passw
     * @return the string
     */
    public String encode(String passw){
        int strenght = 10;
        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
        String encodedPassw = sCryptPasswordEncoder.encode(passw);
        return encodedPassw;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "RegisterUser [prename=" + name + ", lastname=" + lastname + ", password=" + password
                + ", confirmation=" + confirmation + "]";
    }
}
