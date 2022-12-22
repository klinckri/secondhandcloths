package ch.zhaw.secondhandcloths.service;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratStateEnum;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

@Service
public class KaufService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private InseratRepository inseratRepository;

    public void removeItemFromBasket(String inseratId, String email) {
        if (nonNull(inseratId) || nonNull(email)) {
            Optional<Inserat> inserat = inseratRepository.findById(inseratId);
            Person person = personLookup(email);
            if (!inserat.isPresent()) {
                throw new RuntimeException("Cannot find inserat");
            }
            person.getWarenkorb().removeIf(w -> w.getId().equals(inserat.get().getId()));
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

    public void artikelKaufen(String email) {
        Person person = personLookup(email);
        for (Inserat inserat : person.getWarenkorb()) {
            inserat.setInseratState(InseratStateEnum.VERKAUFT);
            inseratRepository.save(inserat);
        }
        person.getWarenkorb().clear();
        personRepository.save(person);
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
