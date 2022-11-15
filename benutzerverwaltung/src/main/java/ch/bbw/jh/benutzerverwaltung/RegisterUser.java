package ch.bbw.jh.benutzerverwaltung;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUser {

    @NotEmpty(message = "prename may not be empty" )
    @Size(min=2, max=24, message="Die Länge des Vornamens: 2 bis 25 Zeichen")
    private String name;


    @NotEmpty (message = "lastname may not be empty" )
    @Size(min=2, max=24, message="Die Länge des Nachnamens: 2 bis 25 Zeichen")
    private String lastname;


    @NotEmpty(message = "password may not be empty" )
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[$@!%?&]).{8,20}$")
    private String password;


    @NotEmpty(message = "confirmation may not be empty" )
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[$@!%?&]).{8,20}$")
    private String confirmation;

    @Size(min = 1000)
    private String message;
    @Size(min = 1000)
    private String email;
    @Size(min = 1000)
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
        this.message = message;
    }

    public Benutzer toUser() {
        Benutzer benutzer = new Benutzer();
        benutzer.setName(name);
        benutzer.setLastname(lastname);
        benutzer.setPhoneNumber(phone);
        benutzer.setEmail(email);
        benutzer.setRole("member");
        benutzer.setBenutzername(name.toLowerCase().trim()+"."+lastname.toLowerCase().trim());
        benutzer.setPassword(password);

        return benutzer;
    }
    public String encode(String passw){
        //TODO
        return null;
    }

    @Override
    public String toString() {
        return "RegisterUser [prename=" + name + ", lastname=" + lastname + ", password=" + password
                + ", confirmation=" + confirmation + "]";
    }
}
