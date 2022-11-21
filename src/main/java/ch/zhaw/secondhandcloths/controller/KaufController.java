package ch.zhaw.secondhandcloths.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.secondhandcloths.model.Inserat;
import ch.zhaw.secondhandcloths.model.Kauf;
import ch.zhaw.secondhandcloths.model.KaufDTO;
import ch.zhaw.secondhandcloths.repository.KaufRepository;

@RestController
@RequestMapping("/api/kauf")
public class KaufController {
    
    @Autowired
    private KaufRepository kaufRepository;

    @PostMapping("/")
    public ResponseEntity<Kauf> artikelKaufen(
            @RequestBody KaufDTO kaufDTO) {
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/warenkorb")
    public ResponseEntity<List<Inserat>> warenkorbAnsehen() {
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
