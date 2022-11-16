package ch.bbw.jh.benutzerverwaltung.user;

import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import ch.bbw.jh.benutzerverwaltung.user.BenutzerGrantedRole;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;

public class BenutzerToDetailsMapper {
    static public UserDetails toUserDetails(Benutzer benutzer) {
        User user = null;

        if(benutzer != null) {
            System.out.println("MemberToUserDetailsMapper.toUserDetails(): user: "+ benutzer);

            java.util.Collection<BenutzerGrantedRole> roles = new ArrayList<>();
            roles.add(new BenutzerGrantedRole(benutzer.getRole()));
            System.out.println("MemberToUserDetailsMapper.toUserDetails(): roles: "+ Arrays.toString(roles.toArray()));

            user = new User(benutzer.getName() + "" + benutzer.getLastname(), benutzer.getPassword(), true, true, true, true, roles);
        }
        return user;
    }
}
