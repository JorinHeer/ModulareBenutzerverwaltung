package ch.bbw.jh.benutzerverwaltung.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;

public class BenutzerToDetailsMapper {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerToDetailsMapper.class);
    static public UserDetails toUserDetails(Benutzer benutzer) {

        java.util.Collection<BenutzerGrantedRole> roles = new ArrayList<>();
        if(benutzer != null) {
            logger.info("UserToUserDetailsMapper.toUserDetails(): user: "+ benutzer);
            roles.add(new BenutzerGrantedRole(benutzer.getRole()));
            logger.info("UserToUserDetailsMapper.toUserDetails(): roles: "+ Arrays.toString(roles.toArray()));

            return new User(benutzer.getName() + "." + benutzer.getLastname(), benutzer.getPassword(), benutzer.isEnabled(), true, true, true, roles);

        }
        return new User(
                " ", " ", true, true, true, true, roles);
    }
}
