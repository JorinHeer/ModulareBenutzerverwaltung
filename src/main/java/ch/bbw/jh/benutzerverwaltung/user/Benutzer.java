package ch.bbw.jh.benutzerverwaltung.user;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * The type Benutzer.
 */
@Entity
@Table(name = "benutzers")
public class Benutzer {
    @Id
    @GeneratedValue(generator = "generatorMember", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "generatorMember", initialValue=20)
    private long id;

    @NotEmpty(message = "name may not be empty" )

    private String name;
    @NotEmpty (message = "lastname may not be empty" )
    private String lastname;

    @NotEmpty(message = "password may not be empty" )
    private String password;

    private String email;

    private String phonenumber;

    private String benutzername;
    private String role;
    private boolean enabled= true;

    /**
     * Instantiates a new Benutzer.
     *
     * @param name        the name
     * @param lastname    the lastname
     * @param password    the password
     * @param email       the email
     * @param phonenumber the phonenumber
     * @param role        the role
     */
    public Benutzer(String name, String lastname, String password, String email, String phonenumber, String role) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        benutzername= name.trim().toLowerCase()+"."+lastname.trim().toLowerCase();
        this.role = role;
    }

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Toggle enabled.
     */
    public void toggleEnabled() {
        if (enabled == false){
        this.enabled = true;
        }else this.enabled = false;
    }

    /**
     * Instantiates a new Benutzer.
     */
    public Benutzer() {

    }

    /**
     * Gets benutzername.
     *
     * @return the benutzername
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * Sets benutzername.
     *
     * @param benutzername the benutzername
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername.toLowerCase();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
     * Gets phonenumber.
     *
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * Sets phonenumber.
     *
     * @param phoneNumber the phone number
     */
    public void setPhonenumber(String phoneNumber) {
        this.phonenumber = phoneNumber;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }
}