package ch.bbw.jh.benutzerverwaltung.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BenutzerService implements UserDetailsService {
    @Autowired
    private BenutzerRepository repository;

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
        System.out.println("UserService:getById(), id does not exist in repository: " + id);
        return null;
    }

    public Benutzer getByUserName(String username) {
        Iterable<Benutzer> useritr = repository.findAll();

        for(Benutzer benutzer : useritr){
            if (benutzer.getBenutzername().equals(username)) {
                return benutzer;
            }
        }
        System.out.println("UserService:getByUserName(), username does not exist in repository: " + username);
        return null;
    }
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Benutzer benutzer = getByUserName(s);
        return BenutzerToDetailsMapper.toUserDetails(benutzer);
    }
}
