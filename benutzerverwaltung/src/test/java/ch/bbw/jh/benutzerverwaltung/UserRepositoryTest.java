package ch.bbw.jh.benutzerverwaltung;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { UserRepository.class })
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindingCustomerById_thenCorrect() {
        userRepository.save(new User("Tomas",
                "Test",
                "1234",
                "tomas.test@test.com",
                "123 456 78 90",
                "Administrator"));
        Assertions.assertEquals(userRepository.findById(1L), Optional.class);
    }

/**    @Test
    public void whenFindingAllCustomers_thenCorrect() {
        userRepository.save(new User("John", "john@domain.com"));
        userRepository.save(new User("Julie", "julie@domain.com"));
        assertThat(userRepository.findAll()).isInstanceOf(List.class);
    }
**/
}
