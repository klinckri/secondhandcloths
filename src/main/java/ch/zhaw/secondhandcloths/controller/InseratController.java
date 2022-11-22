package ch.zhaw.secondhandcloths.controller;

import java.util.List;
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

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratDTO;
import ch.zhaw.secondhandcloths.model.KategorieEnum;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;
import ch.zhaw.secondhandcloths.repository.PersonRepository;

@RestController
@RequestMapping("/api/inserat")
public class InseratController {

    @Autowired
    private InseratRepository inseratRepository;
    @Autowired
    private PersonRepository personRepository;

    @PostMapping("/inserieren")
    public ResponseEntity<Inserat> createInserat(
            @RequestBody InseratDTO inseratDTO) {
                Optional<Person> person = personRepository.findById(inseratDTO.getPersonId());
                if(person.isPresent()) {
                    Inserat inserat = new Inserat(inseratDTO.getTitel(), inseratDTO.getBeschreibung(), inseratDTO.getPreis(), inseratDTO.getIban(), inseratDTO.getKategorie(), person.get());
                    Inserat savedInserat = inseratRepository.save(inserat);
                    return new ResponseEntity<>(savedInserat, HttpStatus.CREATED);
                }
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/home")
    public ResponseEntity<List<Inserat>> getAllInserate() {
        List<Inserat> inserate = inseratRepository.findAll();
        return new ResponseEntity<>(inserate, HttpStatus.OK);
    }

    @GetMapping("/home/{kategorie}")
    public ResponseEntity<List<Inserat>> getAllByKategorie(@PathVariable KategorieEnum kategorie) {
        List<Inserat> inserate = inseratRepository.findByKategorie(kategorie);
        return new ResponseEntity<>(inserate, HttpStatus.OK);
    }

    @GetMapping("/artikel/{id}")
    public ResponseEntity<Inserat> getArtikel(@PathVariable String id) {
        Optional<Inserat> inserat = inseratRepository.findById(id);
        if(inserat.isPresent()) {
            return new ResponseEntity<>(inserat.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
