package ch.zhaw.secondhandcloths.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.service.KaufService;

@RestController
@RequestMapping("/api/kauf")
public class KaufController {

    @Autowired 
    private KaufService kaufService;

    /*@PostMapping("")
    public ResponseEntity<Void> artikelKaufen(
            @RequestBody KaufDTO kaufDTO) {
        kaufService.artikelKaufen(kaufDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @GetMapping("/warenkorb")
    public ResponseEntity<List<Inserat>> warenkorb(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = getEmail(jwt);
        List<Inserat> inserate = kaufService.basketOfUser(userEmail);
        return new ResponseEntity<>(inserate, HttpStatus.OK);
    }

    @GetMapping("/warenkorbSize")
    public ResponseEntity<Integer> warenkorbSize(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = getEmail(jwt);
        List<Inserat> inserate = kaufService.basketOfUser(userEmail);
        return new ResponseEntity<>(inserate.size(), HttpStatus.OK);
    }

    @PostMapping("/removeFromBasket/{id}")
    public ResponseEntity<Void> removeFromBasket(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String userEmail = getEmail(jwt);
        kaufService.removeItemFromBasket(id, userEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addToWarenkorb/{id}")
    public ResponseEntity<Void> addToWarenkorb(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        String userEmail = getEmail(jwt);
        kaufService.addToWarenkorb(id, userEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String getEmail(Jwt jwt) {
        return jwt.getClaimAsString("email");
    }
}
