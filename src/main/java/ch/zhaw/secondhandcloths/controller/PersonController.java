package ch.zhaw.secondhandcloths.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.secondhandcloths.model.LoginDTO;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.model.PersonDTO;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/registrieren")
    public ResponseEntity<Person> createPerson(
            @RequestBody PersonDTO personDTO) {
        Person person = new Person(personDTO.getVorname(), personDTO.getNachname(), personDTO.getEmail(),
                personDTO.getStrasse(), personDTO.getPlz(), personDTO.getOrt(), personDTO.getPasswort());
        Person savedPerson = personRepository.save(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Person> login(
            @RequestBody LoginDTO loginDto) {
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
