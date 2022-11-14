package ch.bbw.jh.benutzerverwaltung;

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

                user = new Benutzer(benutzer.getName(), benutzer.getLastname(), benutzer.getPassword(), benutzer.getEmail(), benutzer.getPhoneNumber(), roles);
            }
            return user;
        }
    }
}
