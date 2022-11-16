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
        User user = null;

        if(benutzer != null) {
            logger.info("UserToUserDetailsMapper.toUserDetails(): user: "+ benutzer);
            java.util.Collection<BenutzerGrantedRole> roles = new ArrayList<>();
            roles.add(new BenutzerGrantedRole(benutzer.getRole()));
            logger.info("UserToUserDetailsMapper.toUserDetails(): roles: "+ Arrays.toString(roles.toArray()));

            user = new User(benutzer.getName() + "." + benutzer.getLastname(), benutzer.getPassword(), true, true, true, true, roles);
        }
        return user;
    }
}
