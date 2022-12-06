package ch.zhaw.secondhandcloths.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratDTO;
import ch.zhaw.secondhandcloths.model.InseratOutput;
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

    @PostMapping(value = "/inserieren")
    public ResponseEntity<Inserat> createInserat(
            @ModelAttribute InseratDTO inseratDTO) throws IOException {
        Optional<Person> person = personRepository.findById(inseratDTO.getPersonId());
        if (person.isPresent()) {
            //file
            String pictureID = UUID.randomUUID().toString();
            String type = inseratDTO.getFile().getContentType().replace("image/", "");
            Path path = Paths.get("src/main/resources/pictures");
            
            //File filename = new File(path + pictureID);
            FileUtils.writeByteArrayToFile(new File(path + "/" + pictureID + "." + type), inseratDTO.getFile().getBytes());

            // save
            Inserat inserat = new Inserat(inseratDTO.getTitel(), inseratDTO.getBeschreibung(), inseratDTO.getPreis(),
                    inseratDTO.getIban(), inseratDTO.getKategorie(), person.get(),
                    pictureID);
            Inserat savedInserat = inseratRepository.save(inserat);
            return new ResponseEntity<>(savedInserat, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/home")
    public ResponseEntity<List<InseratOutput>> getAllInserate() {
        List<Inserat> inserate = inseratRepository.findAll();
        List<InseratOutput> iOutputs = new ArrayList<>();
        for(Inserat i : inserate) {
            File file = null;
            if(Objects.nonNull(i.getFilename())) {
                Path path = Paths.get("src/main/resources/pictures/" + i.getFilename());
                file = path.toFile();
            }
            iOutputs.add(new InseratOutput(i.getId(), i.getTitel(), i.getBeschreibung(), i.getPreis(), i.getIban(), i.getKategorie(), i.getPersonId(), file));
        }
        return new ResponseEntity<>(iOutputs, HttpStatus.OK);
    }

    @GetMapping("/home/{kategorie}")
    public ResponseEntity<List<Inserat>> getAllByKategorie(@PathVariable KategorieEnum kategorie) {
        List<Inserat> inserate = inseratRepository.findByKategorie(kategorie);
        return new ResponseEntity<>(inserate, HttpStatus.OK);
    }

    @GetMapping("/artikel/{id}")
    public ResponseEntity<Inserat> getArtikel(@PathVariable String id) {
        Optional<Inserat> inserat = inseratRepository.findById(id);
        if (inserat.isPresent()) {
            return new ResponseEntity<>(inserat.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{person}")
    public ResponseEntity<List<Inserat>> getAllByKategorie(@PathVariable String person) {
        Optional<Person> personId = personRepository.findById(person);
        List<Inserat> inserate = inseratRepository.findByPersonId(personId.get());
        return new ResponseEntity<>(inserate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInserat(@PathVariable String id) {
        System.err.println("it's here: " + id);
        inseratRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
