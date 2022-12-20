package ch.zhaw.secondhandcloths.service;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratStateEnum;
import ch.zhaw.secondhandcloths.model.Kauf;
import ch.zhaw.secondhandcloths.model.KaufDTO;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;
import ch.zhaw.secondhandcloths.repository.KaufRepository;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

@Service
public class KaufService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private InseratRepository inseratRepository;
    @Autowired
    private KaufRepository kaufRepository;

    public void removeItemFromBasket(String inseratId, String email) {
        if (nonNull(inseratId) || nonNull(email)) {
            Optional<Inserat> inserat = inseratRepository.findById(inseratId);
            Person person = personLookup(email);
            if (!inserat.isPresent()) {
                throw new RuntimeException("Cannot find inserat");
            }
            List<Inserat> warenkorb = person.getWarenkorb();
            person.getWarenkorb().clear();
            personRepository.save(person);
            warenkorb.remove(inserat.get());
            person.setWarenkorb(warenkorb);
            inserat.get().setInseratState(InseratStateEnum.INSERIERT);
            personRepository.save(person);
            inseratRepository.save(inserat.get());
        } else {
            throw new RuntimeException("Invalid input");
        }

    }

    public void addToWarenkorb(String inseratId, String email) {
        if (nonNull(inseratId) || nonNull(email)) {
            Optional<Inserat> inserat = inseratRepository.findById(inseratId);
            Person person = personLookup(email);
            if (!inserat.isPresent()) {
                throw new RuntimeException("Cannot find inserat");
            }
            inserat.get().setInseratState(InseratStateEnum.WARENKORB);
            person.getWarenkorb().add(inserat.get());
            inseratRepository.save(inserat.get());
            personRepository.save(person);
        } else {
            throw new RuntimeException("Invalid input");
        }

    }

    public void artikelKaufen(KaufDTO kaufDTO) {
        List<Inserat> inserate = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for (String inserat : kaufDTO.getInserate()) {
            Optional<Inserat> foundInserat = inseratRepository.findById(inserat);
            if (foundInserat.isPresent()) {
                inserate.add(foundInserat.get());
            } else {
                errors.add("Artikel: " + inserat + " konnte nicht gefunden werden oder ist bereits verkauft.");
            }
        }
        Optional<Person> person = personRepository.findById(kaufDTO.getPersonId());
        if (person.isPresent()) {
            Kauf kauf = new Kauf(person.get(), inserate);
            kaufRepository.save(kauf);
        } else {
            errors.add("Sie sind nicht eingeloggt.");
        }
    }

    public List<Inserat> basketOfUser(String email) {
        Person person = personLookup(email);
        return person.getWarenkorb();
    }

    private Person personLookup(String email) {
        Optional<Person> person = personRepository.findByEmail(email);
        if(person.isPresent()) {
            return person.get();
        }
        throw new RuntimeException("Cannot find person");
    }
}
