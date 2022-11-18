package ch.bbw.jh.benutzerverwaltung.user;

import org.springframework.security.core.GrantedAuthority;

public class BenutzerGrantedRole implements GrantedAuthority {
    private static final long serialVersionUID = 123123;
    private String authority;

    public BenutzerGrantedRole(String role) {
        super();
        this.authority = role;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return "UserGrantedAuthority [auhtority="+authority+"]";
    }
}