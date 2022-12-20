package ch.zhaw.secondhandcloths.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratStateEnum;
import ch.zhaw.secondhandcloths.model.KategorieEnum;
import ch.zhaw.secondhandcloths.model.Person;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class InseratRepositoryTest {
    @Autowired
    private InseratRepository inseratRepository;
    private Person p1 = null;

    @BeforeAll
    void setup() {
        inseratRepository.deleteAll();
        p1 = new Person("testmail1@gmail.com");
        p1.setId("123456789");
        inseratRepository
                .save(new Inserat("Test 1", "Beschreibung 1", 23.90, KategorieEnum.PULLOVER, p1, "testfile1.jpg"));
        Person p2 = new Person("testmail2@gmail.com");
        p2.setId("987654321");
        Inserat i2 = new Inserat("Test 2", "Beschreibung 2", 23.90, KategorieEnum.HOSEN, p2, "testfile2.jpg");
        i2.setInseratState(InseratStateEnum.WARENKORB);
        inseratRepository.save(i2);
        Inserat i3 = new Inserat("Test 3", "Beschreibung 3", 23.90, KategorieEnum.PULLOVER, p1, "testfile3.jpg");
        i3.setInseratState(InseratStateEnum.VERKAUFT);
        inseratRepository.save(i3);
    }

    @Test
    void testFindByInseratState() {
        // Inseriert
        List<Inserat> inserate1 = inseratRepository.findByInseratState(InseratStateEnum.INSERIERT);
        assertEquals(1, inserate1.size());
        assertEquals("Test 1", inserate1.get(0).getTitel());
        // Warenkorb
        List<Inserat> inserate2 = inseratRepository.findByInseratState(InseratStateEnum.WARENKORB);
        assertEquals(1, inserate2.size());
        assertEquals("Beschreibung 2", inserate2.get(0).getBeschreibung());
        // Verkauft
        List<Inserat> inserate3 = inseratRepository.findByInseratState(InseratStateEnum.VERKAUFT);
        assertEquals(1, inserate3.size());
        assertEquals("testfile3.jpg", inserate3.get(0).getFilename());
    }

    @Test
    void testFindByKategorie() {
        // Hosen
        List<Inserat> inserate1 = inseratRepository.findByKategorie(KategorieEnum.HOSEN);
        assertEquals(1, inserate1.size());
        assertEquals("Test 2", inserate1.get(0).getTitel());
        // Pullover
        List<Inserat> inserate2 = inseratRepository.findByKategorie(KategorieEnum.PULLOVER);
        assertEquals(2, inserate2.size());
        assertEquals("Test 1", inserate2.get(0).getTitel());
        assertEquals("Test 3", inserate2.get(1).getTitel());
        // ANDERE
        List<Inserat> inserate3 = inseratRepository.findByKategorie(KategorieEnum.ANDERE);
        assertEquals(0, inserate3.size());
    }

    @Test
    void testFindByPersonId() {
        List<Inserat> inserate = inseratRepository.findByPersonId(p1);
        assertEquals(2, inserate.size());
        assertEquals("Test 1", inserate.get(0).getTitel());
        assertEquals("Test 3", inserate.get(1).getTitel());
    }
}
