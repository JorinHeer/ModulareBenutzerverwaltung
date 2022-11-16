package ch.bbw.jh.benutzerverwaltung.user;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "benutzers")
public class Benutzer {
    @Id
    @GeneratedValue(generator = "generatorMember", strategy = GenerationType.AUTO)
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
    private String phonenumber;

    private String benutzername;
    private String role;

    public Benutzer(String name, String lastname, String password, String email, String phonenumber, String role) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        benutzername= name.trim().toLowerCase()+"."+lastname.trim().toLowerCase();
        this.role = role;
    }

    public Benutzer() {

    }
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername.toLowerCase();
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phoneNumber) {
        this.phonenumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}