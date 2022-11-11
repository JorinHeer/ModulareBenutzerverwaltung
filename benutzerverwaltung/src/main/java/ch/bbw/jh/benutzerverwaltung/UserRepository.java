package ch.bbw.jh.benutzerverwaltung;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository                                                    //Klasse, id-Typ
public interface UserRepository extends CrudRepository<User, Long> {
    //Da wir eine embedded database verwenden, braucht es keine Conecction Information.
}
