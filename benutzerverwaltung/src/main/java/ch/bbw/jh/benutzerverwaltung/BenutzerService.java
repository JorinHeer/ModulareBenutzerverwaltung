package ch.bbw.jh.benutzerverwaltung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BenutzerService {
    @Autowired
    private BenutzerRepository repository;

    public Iterable<Benutzer> getAll(){

        return repository.findAll();
    }

    public void add(Benutzer member) {
        repository.save(member);
    }

    public void update(Long id, Benutzer member) {
        //save geht auch für update.
        repository.save(member);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Benutzer getById(Long id) {
        Iterable<Benutzer> memberitr = repository.findAll();

        for(Benutzer member: memberitr){
            if (member.getId() == id) {
                return member;
            }
        }
        System.out.println("MemberService:getById(), id does not exist in repository: " + id);
        return null;
    }

    public Benutzer getByName(String username) {
        Iterable<Benutzer> useritr = repository.findAll();

        for(Benutzer benutzer : useritr){
            if (benutzer.getName().equals(username)) {
                return benutzer;
            }
        }
        System.out.println("MemberService:getByUserName(), username does not exist in repository: " + username);
        return null;
    }
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Benutzer benutzer = getByName(s);
        return BenutzerToDetailsMapper.toUserDetails(benutzer);
    }
}
