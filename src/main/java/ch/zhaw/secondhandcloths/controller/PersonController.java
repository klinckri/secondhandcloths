package ch.zhaw.secondhandcloths.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public static Person LOGEDINPERSON;

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

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable String id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return new ResponseEntity<>(person.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Person> login(
            @RequestBody LoginDTO loginDto) {
        Optional<Person> person = personRepository.findByEmail(loginDto.getEmail());
        if(person.isPresent()) {
            if(person.get().getPasswort().equals(loginDto.getPasswort())) {
                LOGEDINPERSON = person.get();
                return new ResponseEntity<>(person.get(), HttpStatus.OK);
            } 
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
