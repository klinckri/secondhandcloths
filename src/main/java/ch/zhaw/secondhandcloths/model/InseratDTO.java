package ch.zhaw.secondhandcloths.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InseratDTO {
    private String titel;
    private String beschreibung;
    private double preis;
    private String iban;
    private KategorieEnum kategorie;
    private String personId;
}