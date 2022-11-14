package ch.bbw.jh.benutzerverwaltung;


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
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindingSingleUser() {
        assertTrue(userRepository.findById(20L).isPresent());
    }

    @Test
    public void testFindingAllUsers() {
        assertTrue(userRepository.findAll() instanceof List);
    }

    @Test
    public void testSavingUser() {
        assertEquals(userRepository.findById(20L).get().getName(), "Tomas");

    }

    @Before
    public void setup() {
        userRepository.save(new User("Tomas",
                "Test",
                "1234",
                "tomas.test@test.com",
                "123 456 78 90",
                "Administrator"));
        userRepository.save(new User("Ane",
                "Amberg",
                "1234",
                "ane.amberg@test.com",
                "123 456 78 90",
                "Administrator"));
    }
}


