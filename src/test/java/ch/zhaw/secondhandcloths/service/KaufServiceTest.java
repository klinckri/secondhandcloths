package ch.zhaw.secondhandcloths.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratStateEnum;
import ch.zhaw.secondhandcloths.model.KategorieEnum;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KaufServiceTest {

    @Autowired
    private KaufService kaufService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private InseratRepository inseratRepository;
    private Person p;
    private Inserat inserat;

    @BeforeAll
    void setup() {
        Optional<Person> pers = personRepository.findByEmail("test@test.com");
        if(pers.isPresent()) {
            personRepository.delete(pers.get());
        }
        p = personRepository.save(new Person("test@test.com"));
        inserat = inseratRepository.save(new Inserat("Test 1", "Beschreibung 1", 32.45, KategorieEnum.ANDERE, p, "test.jpg"));
    }

    @Test
    void testAddToWarenkorb_successful() {
        kaufService.addToWarenkorb(inserat.getId(), "test@test.com");
        assertEquals(1, personRepository.findById(p.getId()).get().getWarenkorb().size());
        assertEquals(InseratStateEnum.WARENKORB, inseratRepository.findById(inserat.getId()).get().getInseratState());
    }

    @Test
    void testAddToWarenkorb_failOnInputParameter() {
        assertThrows(RuntimeException.class, () -> kaufService.addToWarenkorb(null, null));
        assertThrows(RuntimeException.class, () -> kaufService.addToWarenkorb("123", null));
        assertThrows(RuntimeException.class, () -> kaufService.addToWarenkorb(null, "fail@fail.com"));
        assertThrows(RuntimeException.class, () -> kaufService.addToWarenkorb(inserat.getId(), "fail@fail.com"));
        assertThrows(RuntimeException.class, () -> kaufService.addToWarenkorb("1234", "test@test.com"));
    }

    @Test
    void testBasketOfUser_successful() {
        p.setWarenkorb(List.of(new Inserat()));
        personRepository.save(p);
        List<Inserat> inserate = kaufService.basketOfUser("test@test.com");
        assertEquals(1, inserate.size());
    }

    @Test
    void testRemoveItemFromBasket_successful() {
        kaufService.removeItemFromBasket(inserat.getId(), "test@test.com");
        assertEquals(0, personRepository.findById(p.getId()).get().getWarenkorb().size());
        assertEquals(InseratStateEnum.INSERIERT, inseratRepository.findById(inserat.getId()).get().getInseratState());
    }

    @Test
    void testRemoveItemFromBasket_failOnInputParameter() {
        assertThrows(RuntimeException.class, () -> kaufService.removeItemFromBasket(null, null));
        assertThrows(RuntimeException.class, () -> kaufService.removeItemFromBasket("123", null));
        assertThrows(RuntimeException.class, () -> kaufService.removeItemFromBasket(null, "fail@fail.com"));
        assertThrows(RuntimeException.class, () -> kaufService.removeItemFromBasket(inserat.getId(), "fail@fail.com"));
        assertThrows(RuntimeException.class, () -> kaufService.removeItemFromBasket("1234", "test@test.com"));
    }


    @Test
    void testArtikelKaufen() {
        Inserat i = inseratRepository.save(new Inserat("Test 1", "Beschreibung 1", 32.45, KategorieEnum.ANDERE, p, "test.jpg"));
        kaufService.addToWarenkorb(i.getId(), "test@test.com");
        kaufService.artikelKaufen("test@test.com");
        Optional<Inserat> foundInserat = inseratRepository.findById(i.getId());
        assertEquals(foundInserat.get().getInseratState(), InseratStateEnum.VERKAUFT);
    }
}
