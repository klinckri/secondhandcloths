package ch.zhaw.secondhandcloths.controller;

import java.util.List;

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
import ch.zhaw.secondhandcloths.repository.InseratRepository;

@RestController
@RequestMapping("/api/inserat")
public class InseratController {
    
    @Autowired
    private InseratRepository inseratRepository;

    @PostMapping("/inserieren")
    public ResponseEntity<Inserat> createInserat(
            @RequestBody InseratDTO inseratDTO) {
        Inserat inserat = new Inserat(inseratDTO.getTitel(), inseratDTO.getBeschreibung(), inseratDTO.getPreis(), inseratDTO.getIban(), inseratDTO.getPersonId());
        Inserat savedInserat = inseratRepository.save(inserat);
        return new ResponseEntity<>(savedInserat, HttpStatus.CREATED);
    }

    @GetMapping("/home")
    public ResponseEntity<List<Inserat>> getAllInserate() {
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/home/{kategorie}")
    public ResponseEntity<List<Inserat>> getAllByKategorie(@PathVariable String kategorie) {
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/artikel/{id}")
    public ResponseEntity<List<Inserat>> getArtikel(@PathVariable String id) {
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
