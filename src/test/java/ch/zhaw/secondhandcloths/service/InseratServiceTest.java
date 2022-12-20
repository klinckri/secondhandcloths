package ch.zhaw.secondhandcloths.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratDTO;
import ch.zhaw.secondhandcloths.model.InseratStateEnum;
import ch.zhaw.secondhandcloths.model.KategorieEnum;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InseratServiceTest {

    @Autowired
    private InseratService inseratService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private InseratRepository inseratRepository;
    private Person p;

    @BeforeAll
    void start() {
        personRepository.deleteAll();
        p = personRepository.save(new Person("test@test.com"));
    }

    @Test
    void testCreateInserat_successfullyCreated() {
        MockMultipartFile file = new MockMultipartFile("fileuploader", "fineuploader.png", "image/png",
                "Some bytes".getBytes());
        InseratDTO inseratDTO = new InseratDTO("Test Inserat 1", "Inserat fürs Integration Testing 1", 15.40,
                KategorieEnum.ANDERE, file);
        try {
            Inserat inserat = inseratService.createInserat(inseratDTO, "test@test.com");
            assertEquals(inserat.getTitel(), "Test Inserat 1");
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testCreateInserat_failAsFileIsNull() {
        InseratDTO inseratDTO = new InseratDTO("Test Inserat 1", "Inserat fürs Integration Testing 1", 15.40,
                KategorieEnum.ANDERE, null);
        assertThrows(RuntimeException.class, () -> inseratService.createInserat(inseratDTO, "test@test.com"));
    }

    @Test
    void testCreateInserat_failAsFilecontenttypeIsNull() {
        MockMultipartFile file = new MockMultipartFile("fileuploader", "fineuploader.png", null,
                "Some bytes".getBytes());
        InseratDTO inseratDTO = new InseratDTO("Test Inserat 1", "Inserat fürs Integration Testing 1", 15.40,
                KategorieEnum.ANDERE, file);
        assertThrows(RuntimeException.class, () -> inseratService.createInserat(inseratDTO, "test@test.com"));
    }

    @Test
    void testCreateInserat_failAsCannotFindPerson() {
        assertThrows(RuntimeException.class,
                () -> inseratService.createInserat(new InseratDTO(null, null, 0.0, null, null), "fail@fail.com"));
    }

    @Test
    void testGetAllInserate() {
        inseratRepository.deleteAll();
        inseratRepository.save(new Inserat("Test 1", "Beschreibung 1", 32.45, KategorieEnum.ANDERE, p, "test.jpg"));
        inseratRepository.save(new Inserat("Test 2", "Beschreibung 2", 32.45, KategorieEnum.ANDERE, p, "test.jpg"));
        Inserat i = new Inserat("Test 3", "Beschreibung 3", 32.45, KategorieEnum.ANDERE, p, "test.jpg");
        i.setInseratState(InseratStateEnum.WARENKORB);
        inseratRepository.save(i);
        List<Inserat> inserate = inseratService.getAllInserate();
        assertEquals(2, inserate.size());
        assertEquals("Test 1", inserate.get(0).getTitel());
        assertEquals("Test 2", inserate.get(1).getTitel());
    }

    @Test
    void testGetAllInserateFromPerson() {
        inseratRepository.deleteAll();
        inseratRepository.save(new Inserat("Test 1", "Beschreibung 1", 32.45, KategorieEnum.ANDERE, p, "test.jpg"));
        List<Inserat> inserate = inseratService.getAllInserateFromPerson("test@test.com");
        assertEquals(1, inserate.size());
        assertEquals("Test 1", inserate.get(0).getTitel());
    }

    @Test
    void testGetInseratById() {
        Inserat inserat = inseratRepository
                .save(new Inserat("Test 1", "Beschreibung 1", 32.45, KategorieEnum.ANDERE, p, "test.jpg"));
        Inserat foundInserat = inseratService.getInseratById(inserat.getId());
        assertEquals("Test 1", foundInserat.getTitel());
        assertThrows(RuntimeException.class, () -> inseratService.getInseratById("1234567"));
    }

    @Test
    void testValidateUserOnInserat() {
        Inserat inserat = inseratRepository
                .save(new Inserat("Test 1", "Beschreibung 1", 32.45, KategorieEnum.ANDERE, p, "test.jpg"));
        boolean test1 = inseratService.validateUserOnInserat("test@test.com", inserat.getId());
        assertTrue(test1);
        personRepository.save(new Person("test@gmail.com"));
        boolean test2 = inseratService.validateUserOnInserat("test@gmail.com", inserat.getId());
        assertFalse(test2);
        assertThrows(RuntimeException.class, () -> inseratService.validateUserOnInserat("test@test.com", "1234567"));
    }

    @Test
    void testDeleteInserat() {
        Inserat inserat = inseratRepository
                .save(new Inserat("Test 1", "Beschreibung 1", 32.45, KategorieEnum.ANDERE, p, "test.jpg"));
        inseratService.deleteInserat(inserat.getId());
        Optional<Inserat> deletedInserat = inseratRepository.findById(inserat.getId());
        assertFalse(deletedInserat.isPresent());
    }

}
