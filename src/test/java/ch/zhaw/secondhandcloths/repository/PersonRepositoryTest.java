package ch.zhaw.secondhandcloths.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ch.zhaw.secondhandcloths.model.Person;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @BeforeAll
    void setup() {
        personRepository.deleteAll();
        personRepository.save(new Person("testmail1@gmail.com"));
        personRepository.save(new Person("testmail2@gmail.com"));
    }
   @Test
   void testFindByEmail() {
       // Valid mail
       Optional<Person> person1 = personRepository.findByEmail("testmail1@gmail.com");
       assertTrue(person1.isPresent());
       //invalid mail
       Optional<Person> person2 = personRepository.findByEmail("test@gmail.com");
       assertFalse(person2.isPresent());
   }
}
