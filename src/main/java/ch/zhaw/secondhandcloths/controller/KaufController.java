package ch.zhaw.secondhandcloths.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.Kauf;
import ch.zhaw.secondhandcloths.model.KaufDTO;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;
import ch.zhaw.secondhandcloths.repository.KaufRepository;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

@RestController
@RequestMapping("/api/kauf")
public class KaufController {

    @Autowired
    private KaufRepository kaufRepository;
    @Autowired
    private InseratRepository inseratRepository;
    @Autowired
    private PersonRepository personRepository;

    @PostMapping("")
    public ResponseEntity<Kauf> artikelKaufen(
            @RequestBody KaufDTO kaufDTO) {
        List<Inserat> inserate = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for (String inserat : kaufDTO.getInserate()) {
            Optional<Inserat> foundInserat = inseratRepository.findById(inserat);
            if(foundInserat.isPresent()) {
                inserate.add(foundInserat.get());
            } else {
                errors.add("Artikel: " + inserat + " konnte nicht gefunden werden oder ist bereits verkauft.");
            }
        }
        Optional<Person> person = personRepository.findById(kaufDTO.getPersonId());
            if(person.isPresent()) {
                Kauf kauf = new Kauf(person.get(), inserate);
                kaufRepository.save(kauf);
                return new ResponseEntity<>(kauf, HttpStatus.OK);
            } else {
                errors.add("Sie sind nicht eingeloggt.");
            }
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
