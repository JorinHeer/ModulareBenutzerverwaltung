package ch.bbw.jh.benutzerverwaltung;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Benutzer {
    @Id
    @GeneratedValue(generator = "generatorMember", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "generatorMember", initialValue=20)
    private long id;
    private String name;

    private String lastname;


    private String password;
    private String email;
    private String phoneNumber;

    private String benutzername;
    private String role;

    public Benutzer(String name, String lastname, String password, String email, String phoneNumber, String role) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
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