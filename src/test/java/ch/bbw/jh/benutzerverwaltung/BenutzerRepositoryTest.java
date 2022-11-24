package ch.bbw.jh.benutzerverwaltung;


import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import ch.bbw.jh.benutzerverwaltung.user.BenutzerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The type Benutzer repository test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BenutzerRepositoryTest {
    @Autowired
    private BenutzerRepository benutzerRepository;

    /**
     * Test finding single user.
     */
    @Test
    public void testFindingSingleUser() {
        assertTrue(benutzerRepository.findById(20L).isPresent());
    }

    /**
     * Test finding all users.
     */
    @Test
    public void testFindingAllUsers() {
        assertTrue(benutzerRepository.findAll() instanceof List);
    }

    /**
     * Test saving user.
     */
    @Test
    public void testSavingUser() {
        assertEquals(benutzerRepository.findById(20L).get().getName(), "Tomas");

    }

    /**
     * Test delete user.
     */
    @Test
    public void testDeleteUser() {
        benutzerRepository.deleteById(20L);
        Assert.assertEquals(benutzerRepository.findById(20L), Optional.empty());

    }

    /**
     * Sets .
     */
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


