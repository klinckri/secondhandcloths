package ch.zhaw.secondhandcloths.model;


import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class InseratDTO implements Serializable{
    private String titel;
    private String beschreibung;
    private double preis;
    private String iban;
    private KategorieEnum kategorie;
    private MultipartFile file;
}
