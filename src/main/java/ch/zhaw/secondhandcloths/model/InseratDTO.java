package ch.zhaw.secondhandcloths.model;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InseratDTO {
    private String titel;
    private String beschreibung;
    private double preis;
    private KategorieEnum kategorie;
    private MultipartFile file;
}
