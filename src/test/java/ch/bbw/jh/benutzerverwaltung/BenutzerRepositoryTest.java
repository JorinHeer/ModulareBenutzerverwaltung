package ch.bbw.jh.benutzerverwaltung;


import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import ch.bbw.jh.benutzerverwaltung.user.BenutzerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BenutzerRepositoryTest {
    @Autowired
    private BenutzerRepository benutzerRepository;

    @Test
    public void testFindingSingleUser() {
        assertTrue(benutzerRepository.findById(20L).isPresent());
    }

    @Test
    public void testFindingAllUsers() {
        assertTrue(benutzerRepository.findAll() instanceof List);
    }

    @Test
    public void testSavingUser() {
        assertEquals(benutzerRepository.findById(20L).get().getName(), "Tomas");

    }

    @Before
    public void setup() {
        benutzerRepository.save(new Benutzer("Tomas",
                "Test",
                "1234",
                "tomas.test@test.com",
                "123 456 78 90",
                "Administrator"));
        benutzerRepository.save(new Benutzer("Ane",
                "Amberg",
                "1234",
                "ane.amberg@test.com",
                "123 456 78 90",
                "Administrator"));
    }
}


