package ch.bbw.jh.benutzerverwaltung.user;

import ch.bbw.jh.benutzerverwaltung.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
public class BenutzerService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(BenutzerService.class);
    @Autowired
    private BenutzerRepository repository;
    @Autowired
    private LoginAttemptService loginAttemptService;
    @Autowired
    private HttpServletRequest request;

    public Iterable<Benutzer> getAll(){

        return repository.findAll();
    }

    public void add(Benutzer benutzer) {
        repository.save(benutzer);
    }

    public void update(Long id, Benutzer benutzer) {
        //save geht auch für update.
        repository.save(benutzer);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Benutzer getById(Long id) {
        Iterable<Benutzer> useritr = repository.findAll();

        for(Benutzer benutzer: useritr){
            if (benutzer.getId() == id) {
                return benutzer;
            }
        }
        logger.info("UserService:getById(), id does not exist in repository: " + id);
        return null;
    }

    public Benutzer getByUserName(String username) {
        Iterable<Benutzer> useritr = repository.findAll();

        for(Benutzer benutzer : useritr){
            if (benutzer.getBenutzername().equals(username)) {
                return benutzer;
            }
        }
        return null;
    }
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Benutzer benutzer = getByUserName(s);
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        try {
            return BenutzerToDetailsMapper.toUserDetails(benutzer);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
