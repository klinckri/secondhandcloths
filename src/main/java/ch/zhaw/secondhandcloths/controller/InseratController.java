package ch.zhaw.secondhandcloths.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.InseratDTO;
import ch.zhaw.secondhandcloths.model.InseratStateEnum;
import ch.zhaw.secondhandcloths.model.KategorieEnum;
import ch.zhaw.secondhandcloths.model.Person;
import ch.zhaw.secondhandcloths.repository.InseratRepository;
import ch.zhaw.secondhandcloths.repository.PersonRepository;
import ch.zhaw.secondhandcloths.security.UserValidator;

@RestController
@RequestMapping("/api/inserat")
public class InseratController {

    @Autowired
    private InseratRepository inseratRepository;
    @Autowired
    private PersonRepository personRepository;

    @PostMapping(value = "/inserieren")
    public ResponseEntity<Inserat> createInserat(
            @ModelAttribute InseratDTO inseratDTO, @AuthenticationPrincipal Jwt jwt) throws IOException {
        String userEmail = jwt.getClaimAsString("email");
        Optional<Person> person = personRepository.findByEmail(userEmail);
        if (person.isPresent()) {
            // file
            String pictureID = UUID.randomUUID().toString();
            String type = inseratDTO.getFile().getContentType().replace("image/", "");
            Path path = Paths.get("src/main/resources/static/pictures");

            // File filename = new File(path + pictureID);
            FileUtils.writeByteArrayToFile(new File(path + "/" + pictureID + "." + type),
                    inseratDTO.getFile().getBytes());
            String filename = pictureID + "." + type;
            // save
            Inserat inserat = new Inserat(inseratDTO.getTitel(), inseratDTO.getBeschreibung(), inseratDTO.getPreis(),
                    inseratDTO.getIban(), inseratDTO.getKategorie(), person.get(),
                    filename);
            Inserat savedInserat = inseratRepository.save(inserat);
            return new ResponseEntity<>(savedInserat, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/home")
    public ResponseEntity<List<Inserat>> getAllInserate() {
        List<Inserat> inserate = inseratRepository.findByInseratState(InseratStateEnum.INSERIERT);
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
        if (inserat.isPresent()) {
            return new ResponseEntity<>(inserat.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("")
    public ResponseEntity<List<Inserat>> getAllInserateFromPerson(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getClaimAsString("email");
        Optional<Person> person = personRepository.findByEmail(userEmail);
        List<Inserat> inserate = inseratRepository.findByPersonId(person.get());
        return new ResponseEntity<>(inserate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInserat(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        
        String userEmail = jwt.getClaimAsString("email");
        Optional<Person> person = personRepository.findByEmail(userEmail);
        Optional<Inserat> inserat = inseratRepository.findById(id);

        if(person.isPresent() && inserat.isPresent() && !person.get().getId().equals(inserat.get().getPersonId().getId()) && !UserValidator.userHasRole(jwt, "admin")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        inseratRepository.delete(inserat.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
