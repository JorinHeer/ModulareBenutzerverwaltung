package ch.bbw.jh.benutzerverwaltung;

import ch.bbw.jh.benutzerverwaltung.user.Benutzer;
import ch.bbw.jh.benutzerverwaltung.user.BenutzerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BenutzerServiceTest {
    @Autowired
    private BenutzerService benutzerService;


    @Test()
    public void testGetByUserNameNotFound(){

        Assert.assertNull(benutzerService.getByUserName("name"));
    }
    @Test()
    public void testGetByUserNameFound(){

        Assert.assertTrue(benutzerService.getByUserName("tomas.test") instanceof Benutzer);
    }
    @Test()
    public void testLoadByUserName(){

        Assert.assertEquals("Tomas.Test", benutzerService.loadUserByUsername("tomas.test").getUsername());
    }
    @Before
    public void setup() {
        benutzerService.add(new Benutzer("Tomas",
                "Test",
                "1234",
                "tomas.test@test.com",
                "123 456 78 90",
                "Administrator"));
        benutzerService.add(new Benutzer("Ane",
                "Amberg",
                "1234",
                "ane.amberg@test.com",
                "123 456 78 90",
                "Administrator"));
    }
}
