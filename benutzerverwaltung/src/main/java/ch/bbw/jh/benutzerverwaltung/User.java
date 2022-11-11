package ch.bbw.jh.benutzerverwaltung;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "generatorMember", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generatorMember", initialValue=20)
    private long id;
    @NotEmpty(message = "prename may not be empty" )
    @Size(min=2, max=24, message="Die Länge des Vornamens muss 2 bis 25 Zeichen sein.")
    private String name;

    @NotEmpty (message = "lastname may not be empty" )
    @Size(min=2, max=24, message="Die Länge des Nachnamens 2 bis 25 Zeichen sein.")
    private String lastname;


    private String password;
    private String email;
    private String phoneNumber;

    private String role;

    public User(String name, String lastname, String password, String email, String phoneNumber, String role) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
