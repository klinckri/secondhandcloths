package ch.zhaw.secondhandcloths.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class InseratDTO {
    private String titel;
    private String beschreibung;
    private double preis;
    private String iban;
    private KategorieEnum kategorie;
    private String personId;
}
