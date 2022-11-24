package ch.bbw.jh.benutzerverwaltung.user;

import org.springframework.security.core.GrantedAuthority;

/**
 * The type Benutzer granted role.
 */
public class BenutzerGrantedRole implements GrantedAuthority {
    private static final long serialVersionUID = 123123;
    private String authority;

    /**
     * Instantiates a new Benutzer granted role.
     *
     * @param role the role
     */
    public BenutzerGrantedRole(String role) {
        super();
        this.authority = role;
    }

    /**
     * Sets authority.
     *
     * @param authority the authority
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * Gets authority.
     *
     * @return the authority
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "UserGrantedAuthority [auhtority="+authority+"]";
    }
}