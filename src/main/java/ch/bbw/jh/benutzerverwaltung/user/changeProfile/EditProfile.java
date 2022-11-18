package ch.bbw.jh.benutzerverwaltung.user.changeProfile;

import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditProfile {
    @NotEmpty(message = "name may not be empty" )
    @Size(min=2, max=24, message="Die Länge des Vornamens: 2 bis 25 Zeichen")
    private String name;


    @NotEmpty (message = "lastname may not be empty" )
    @Size(min=2, max=24, message="Die Länge des Nachnamens: 2 bis 25 Zeichen")
    private String lastname;

    private String password;

    private String confirmation;

    private String message;
    @Email(message = "gib eine gültige E-Mail Adresse ein")
    private String email;
    @Pattern(regexp = "^^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "gib eine gülige Telefonnummer an")
    private String phone;

    private String role;

    private long id;


    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public EditProfile toChangeProfile(Benutzer user){
        this.setLastname(user.getLastname());
        this.setName(user.getName());
        this.setPassword("");
        this.setEmail(user.getEmail());
        this.setPhone(user.getPhonenumber());
        this.setRole(user.getRole());
        this.setId(user.getId());
        return this;
    }
    public Benutzer toUser() {
        Benutzer benutzer = new Benutzer();
        benutzer.setName(name);
        benutzer.setLastname(lastname);
        benutzer.setPhonenumber(phone);
        benutzer.setEmail(email);
        benutzer.setRole(role);
        benutzer.setBenutzername(name.toLowerCase().trim()+"."+lastname.toLowerCase().trim());
        benutzer.setPassword(encode(password));

        return benutzer;
    }
    public String encode(String passw){
        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
        String encodedPassw = sCryptPasswordEncoder.encode(passw);
        return encodedPassw;
    }
}
