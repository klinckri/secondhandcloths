package ch.zhaw.secondhandcloths.controller;

import java.io.IOException;
import java.util.List;
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
import ch.zhaw.secondhandcloths.security.UserValidator;
import ch.zhaw.secondhandcloths.service.InseratService;

@RestController
@RequestMapping("/api/inserat")
public class InseratController {

    @Autowired
    private InseratService inseratService;

    @PostMapping(value = "/inserieren")
    public ResponseEntity<Inserat> createInserat(
            @ModelAttribute InseratDTO inseratDTO, @AuthenticationPrincipal Jwt jwt) throws IOException {
        String userEmail = getEmail(jwt);
        Inserat inserat = inseratService.createInserat(inseratDTO, userEmail);
        return new ResponseEntity<>(inserat, HttpStatus.CREATED);
    }

    @GetMapping("/home")
    public ResponseEntity<List<Inserat>> getAllInserate() {
        return new ResponseEntity<>(inseratService.getAllInserate(), HttpStatus.OK);
    }

    @GetMapping("/artikel/{id}")
    public ResponseEntity<Inserat> getArtikel(@PathVariable String id) {
        return new ResponseEntity<>(inseratService.getInseratById(id), HttpStatus.OK);
    }

    @GetMapping("/person")
    public ResponseEntity<List<Inserat>> getAllInserateFromPerson(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = getEmail(jwt);
        List<Inserat> inserate = inseratService.getAllInserateFromPerson(userEmail);
        return new ResponseEntity<>(inserate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteInserat(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String userEmail = getEmail(jwt);

        if (!inseratService.validateUserOnInserat(userEmail, id) && !UserValidator.userHasRole(jwt, "admin")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        inseratService.deleteInserat(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getEmail(Jwt jwt) {
        return jwt.getClaimAsString("email");
    }

}
