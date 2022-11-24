package ch.bbw.jh.benutzerverwaltung.user;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Benutzer repository.
 */
@Repository                                                    //Klasse, id-Typ
public interface BenutzerRepository extends CrudRepository<Benutzer, Long> {
    //Da wir eine embedded database verwenden, braucht es keine Conecction Information.
}

